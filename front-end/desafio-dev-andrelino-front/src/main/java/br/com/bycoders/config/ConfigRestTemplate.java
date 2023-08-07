package br.com.bycoders.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigRestTemplate {

	    @Bean
	    public RestTemplate restTemplate() {
	        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
	        requestFactory.setConnectTimeout(15000); 
	        requestFactory.setReadTimeout(15000); 

	        return new RestTemplate(requestFactory);
	    }
	

}
