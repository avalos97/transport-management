package com.ingenio.transportmanagementservice.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Value("${springdoc.swagger-ui.path}")
    private String swaggerPath;

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .pathsToExclude("/private/**", "/error/**")
                .build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController(swaggerPath,
                swaggerPath + "/index.html?url=/transport-management-service/v3/api-docs");
        registry.addRedirectViewController(swaggerPath + "/index.html",
                swaggerPath + "/index.html?url=/transport-management-service/v3/api-docs");
    }
}
