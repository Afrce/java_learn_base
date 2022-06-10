package net.xdclass.xdvideo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig {
    public static String appSecretConfig;
    public static Integer expiresConfig; // token 有效期天数
    public static String subjectConfig; // 签发方

    private String appSecret;
    private Integer expires; // token 有效期天数
    private String subject; // 签发方

    @PostConstruct
    public void setAppSecret() {
        appSecretConfig = this.appSecret;
    }

    @PostConstruct
    public void setExpires() {
        expiresConfig = this.expires;
    }

    @PostConstruct
    public void subject() {
        subjectConfig = this.subject;
    }
}
