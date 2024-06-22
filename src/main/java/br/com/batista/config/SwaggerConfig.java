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
		return GroupedOpenApi.builder().group("crud-app-spring").pathsToMatch("/api/**").build();
	}

	@Bean
	public OpenAPI crudAppSpring() {
		return new OpenAPI()
				.info(new Info().title("CRUD Apring API").description("Spring CRUD sample application").version("v1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("Example Wiki Documentation")
						.url("https://github.com"));
	}

}