package br.com.desafiodev.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@OpenAPIDefinition(info =
@Info(title = "Desafio Dev API",
        version = "v1",
        description = "Documentanção da API Desafiio Dev Service"))
public class OpenApiConfiguration implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                        .title("Desafio Dev API")
                        .version("v1")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("http://springdoc.org")
                        )
                );
    }
}
