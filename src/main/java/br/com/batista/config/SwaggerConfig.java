package br.com.batista.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("vehicle-management-api").pathsToMatch("/api/**").build();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Vehicle Management API")
						.description("API for managing vehicles, brands, and models in a dealership system")
						.version("1.0.0")
						.license(new License().name("Apache 2.0")
								.url("https://www.apache.org/licenses/LICENSE-2.0.html")))
				.externalDocs(new ExternalDocumentation().description("Vehicle Management API Documentation")
						.url("https://github.com/gustavobf/vehicle-management-api"));
	}

}