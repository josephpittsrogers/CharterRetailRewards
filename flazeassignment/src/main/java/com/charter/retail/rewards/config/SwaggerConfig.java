package com.charter.retail.rewards.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
* Swagger Configuration  
* 
* Creates a Docket Bean and established te controller package as the base package for /swagger-ui/
* @author JoeRogers
* 
*/
@Configuration
@Data
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket produceApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.charter.retail.rewards.controller"))
				.build();
	}
	
}
