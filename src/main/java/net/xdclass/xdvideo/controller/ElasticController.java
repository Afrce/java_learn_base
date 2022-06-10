package net.xdclass.xdvideo.controller;

import net.xdclass.xdvideo.dao.UserEsDao;
import net.xdclass.xdvideo.model.bo.UserBo;
import net.xdclass.xdvideo.service.UserEsService;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ElasticController {

    @Autowired
    private UserEsService userEsService;

    @GetMapping("/es/save")
    public Object saveUser() throws IOException {
        UserBo userBo = new UserBo();
        userBo.setId(1L);
        userBo.setEmail("test");
        userBo.setUsername("test");
        IndexResponse response = userEsService.save(userBo);
        return response;
    }

    @GetMapping("/es/get/{id}")
    public Object getUser(@PathVariable String id) throws IOException {
        return userEsService.get(id);
    }

    @GetMapping("/es/delete/{id}")
    public Object deleteUser(@PathVariable String id) throws IOException {
        return userEsService.delete(id);
    }

    @GetMapping("/es/update/{id}")
    public Object updateUser(@PathVariable Long id) throws IOException {
        UserBo userBo = new UserBo();
        userBo.setId(id);
        userBo.setEmail("test");
        userBo.setUsername("update");
        return userEsService.update(userBo);
    }
}
