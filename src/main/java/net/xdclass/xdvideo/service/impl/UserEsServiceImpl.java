package net.xdclass.xdvideo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.xdclass.xdvideo.model.bo.UserBo;
import net.xdclass.xdvideo.service.UserEsService;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserEsServiceImpl implements UserEsService {
    @Resource
    private RestHighLevelClient client;

    @Override
    public Object get(String id) throws IOException {
        GetRequest getRequest = new GetRequest("user");
        getRequest.id(id);
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        return response.getSourceAsMap();
    }


    @Override
    public Object delete(String id) throws IOException{
        DeleteRequest deleteRequest = new DeleteRequest("user", id);
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
        return response;
    }

    @Override
    public IndexResponse save(UserBo userBo) throws IOException {
        IndexRequest index = new IndexRequest("user").id(String.valueOf(userBo.getId()))
                .source(beanToMap(userBo));
        IndexResponse response = client.index(index, RequestOptions.DEFAULT);
        return response;
    }

    @Override
    public Object update(UserBo userBo) throws IOException{
        UpdateRequest updateRequest = new UpdateRequest("user", String.valueOf(userBo.getId()))
                .doc(beanToMap(userBo));
        UpdateResponse response = client.update(updateRequest, RequestOptions.DEFAULT);
        return response;
    }

    public Object search() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.boolQuery()
                .must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("username", "test"))
                        .should(QueryBuilders.matchQuery("email", ""))
                )
                .must(QueryBuilders.boolQuery()
                        .should(QueryBuilders.matchQuery("username", "test"))
                        .should(QueryBuilders.matchQuery("email", ""))
                )
        );
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        List<UserBo> userList = new LinkedList<>();

        for (SearchHit searchHit : hits) {
            UserBo userBo = JSONObject.parseObject((InputStream) searchHit.getSourceAsMap(), UserBo.class);
            userList.add(userBo);
        }
        return userList;
    }

    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if (beanMap.get(key) != null) {
                    map.put(key + "", beanMap.get(key));
                }
            }
        }
        System.out.println(JSON.toJSONString(map));
        return map;
    }
}
