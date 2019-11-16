package com.races.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration
 * 
 * @author Maximino Mañanes Ruiz
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Get Api Info of Onboarding Services
	 * 
	 * @return
	 */
	private ApiInfo onboardingApiInfo() {
		return new ApiInfoBuilder().title("Authentication Services REST API").version("1.0").build();
	}

	/**
	 * Get admitted paths
	 * 
	 * @return
	 */
	private Predicate<String> onboardingPaths() {
		return springfox.documentation.builders.PathSelectors.regex("(/user.*)");
	}

	/**
	 * Publish a bean to generate swagger2 endpoints
	 * 
	 * @return a swagger configuration bean
	 */
	@Bean
	public Docket onboardingApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(onboardingApiInfo()).select().paths(onboardingPaths())
				.apis(RequestHandlerSelectors.any()).build().useDefaultResponseMessages(false);
	}
}
