package com.rmurugaian.myrewards.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI gatewaySwagger(final BuildProperties buildProperties) {
        final SecurityScheme securitySchemesItem =
            new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("basic");

        return new OpenAPI()
            .components(new Components().addSecuritySchemes("basicAuth", securitySchemesItem))
            .info(info(buildProperties));
    }

    private Info info(final BuildProperties buildProperties) {

        return new Info().title("Rewards Management API")
            .version(buildProperties.getVersion())
            .description("route configuration operations");
    }

}
