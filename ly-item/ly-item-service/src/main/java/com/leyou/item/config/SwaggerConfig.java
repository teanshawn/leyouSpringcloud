package com.leyou.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("开发小组名称")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/brand/**"))
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("scofi", "https://program-think.blogspot.com/", "teanshawn@roc.com");
        return new ApiInfo("swagger测试",
                "品牌controller测试",
                "1.0",
                "https://github.com/termsOfServiceUrl",
                contact,                                   // 作者信息
                "Apache 2.0",
                "ttps://github.com/licenseUrl",
                new ArrayList());
    }
}
