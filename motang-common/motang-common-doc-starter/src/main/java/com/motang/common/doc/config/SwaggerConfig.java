package com.motang.common.doc.config;

import com.motang.common.doc.properties.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @Description  swagger自动注入配置
 * ConditionalOnProperty 当且仅当属性为true时会把当前类注入ioc
 * EnableConfigurationProperties会把被ConfigurationProperties标注的类注入ioc
 * @author liuhu
 * @Date 2020/12/16 21:42
 */
@EnableSwagger2
@Configuration
@ConditionalOnProperty(value = "motang.swagger.enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title(swaggerProperties.getTitle())
                        .description(swaggerProperties.getDescription())
                        .version(swaggerProperties.getVersion())
                        .contact(new Contact(swaggerProperties.getName(),swaggerProperties.getUrl(),swaggerProperties.getEmail()))
                        .license(swaggerProperties.getLicense())
                        .licenseUrl(swaggerProperties.getLicenseUrl())
                        .build());
    }
}
