package com.ws.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig
{
@Bean
public GroupedOpenApi controllerApi() {
    return GroupedOpenApi.builder()
            .group("irctc-api")
            .packagesToScan("com.ws.controller") 
            .build();
}
}
