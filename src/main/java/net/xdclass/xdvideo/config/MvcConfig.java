package net.xdclass.xdvideo.config;

import net.xdclass.xdvideo.interceoter.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/*/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    // 跨域相关
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","PUT","POST", "PATCH")
                .allowCredentials(true)
                .maxAge(3600);
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
