package net.xdclass.xdvideo.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
@Data
public class EsConfig {
    private String host;
    private String port;
    private String admin;
    private String password;
    private String analyzer;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient getClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, Integer.parseInt(port), "http")));
    }
}
