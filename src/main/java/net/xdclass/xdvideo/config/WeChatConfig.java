package net.xdclass.xdvideo.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 微信相关的配置
 */
@Configuration
@ConfigurationProperties(prefix = "wechat")
@Data
public class WeChatConfig implements InitializingBean {
    public static String APPIDC;

    private String appid;
    private String appSecret;

    @Override
    public void afterPropertiesSet() throws Exception {
        APPIDC = this.appid;
    }
}
