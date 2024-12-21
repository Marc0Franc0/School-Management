package com.api.notemanagementapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringDocConfiguration {
    //Bean para la configuración JWT en los endpoints de la documentación
    @Bean
    public OpenAPI customOpenApi(
            @Value("${openapi.service.title}") String serviceTitle){
        return new OpenAPI()
                .info(new Info().title(serviceTitle));
    }

}
