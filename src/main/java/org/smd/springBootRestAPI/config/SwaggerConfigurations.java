package org.smd.springBootRestAPI.config;

import java.util.Arrays;

import org.smd.springBootRestAPI.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

	@Bean
	public Docket forumApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.smd.springBootRestAPI"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.ignoredParameterTypes(User.class)
				.globalOperationParameters(Arrays.asList(
						new ParameterBuilder()
						.name("Authorization")
						.description("Header for JWT Token")
						.modelRef(new ModelRef("string"))
						.parameterType("header")
						.required(false)
						.build(
						)
					)
				)
				;
	}
}
