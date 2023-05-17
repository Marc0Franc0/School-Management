package com.api.notemanagementapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
        .info(new Info()
        .title("NOTE-MANAGEMENT-API")
        .description("Notes manager for students"));
    }
    
}
