package com.springrest.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	// This is used for configuring group of apis GroupedOpenApi is similar to Docket in Swagger2
	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder()
				.packagesToScan("com.springrest")
				.group("spring-rest")
				.build();
	}

	// This is used to give extra info in swagger ui page OpenAPI is similar to APIInfo in Swagger2
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("SpringRest API")
						.description("Spring Project created to learn REST")
						.version("v1.0.0"));
	}
}
