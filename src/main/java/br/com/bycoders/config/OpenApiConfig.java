package br.com.bycoders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    
	  @Bean
	  public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	      .info(new Info().title("API Calculo de IMC"))
	      .components(new Components());
	    //http://localhost:8080/swagger-ui/index.html
	  }
}
