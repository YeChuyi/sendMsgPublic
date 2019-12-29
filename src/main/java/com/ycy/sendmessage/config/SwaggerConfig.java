package com.ycy.sendmessage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

/**
 * Swagger2的接口配置
 *
 * @author yechuyi
 */
@Configuration
@EnableSwagger2
@ConditionalOnBean(SwaggerConfig.class)
public class SwaggerConfig {

    @Autowired
    private AppConfig appConfig;


    /**
     * 全局设置Content Type，默认是application/json
     * 如果想只针对某个方法，则注释掉改语句，在特定的方法加上下面信息
     * @ApiOperation(consumes="application/x-www-form-urlencoded")
     */
    public static final HashSet<String> consumes = new HashSet<String>() {{
        add("application/x-www-form-urlencoded");
    }};

    @Bean(value = "commonApi")
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("通用公共类接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ycy.sendmessage.controller"))
                .paths(PathSelectors.any())
                .build()
                //.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.<SecurityScheme> newArrayList(apiKey()))
                .consumes(consumes);
    }


    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                .title("发送手机短信接口文档")
                .contact(new Contact(appConfig.name, null, null))
                .version("版本号:" + appConfig.version)
                .build();
    }

   /* private ApiKey apiKey() {
        return new ApiKey("AccessToken", "AccessToken", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("AccessToken", authorizationScopes));
    }*/
}
