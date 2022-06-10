package net.xdclass.xdvideo.config;

import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Component
@Data
@ConfigurationProperties(prefix = "swagger")
@EnableOpenApi
public class SwaggerConfig {
    private Boolean enable;

    private String applicationName;

    private String applicationDescription;

    private String applicationVersion;

    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .pathMapping("/")
                .enable(enable)
                .apiInfo(apiInfo())
                // 选择哪些接口作为文档发步
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(applicationName)
                .description(applicationDescription)
                .version(applicationVersion)
                .termsOfServiceUrl("")
                .build();
    }
}
