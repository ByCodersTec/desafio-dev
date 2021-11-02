package com.github.filipednb.financialtransactions;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Transactions API", version = "1.0")
)
public class FinancialTransactionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialTransactionsApplication.class, args);
	}

}
