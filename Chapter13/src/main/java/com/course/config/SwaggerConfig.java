package com.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build()
                ;
    }
//以下是swagger ui生产的接口文档的接口说明，
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("UserManager service Api")
                .contact(new Contact("dingzhi","","443155612@qq.com"))
                .description("this in UserManager service Api")
                .version("1.0")
                .build()
                ;
    }
}
