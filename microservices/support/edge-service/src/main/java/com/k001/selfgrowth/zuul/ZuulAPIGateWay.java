package com.k001.selfgrowth.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableZuulProxy
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ZuulAPIGateWay {

	public static void main(String[] args) { SpringApplication.run(ZuulAPIGateWay.class, args); }
//
//	@Bean
//	UiConfiguration uiConfig() {
//		return new UiConfiguration("validatorUrl", "list", "alpha", "schema",
//				UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS, false, true, 60000L);
//	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Diary REST API",
				"Diary Service",
				"API TOS",
				"Terms of service",
				new Contact("K001", "www.k001.com", "selfgrowth@selfgrowth.com"),
				"License of API", "API license URL", Collections.emptyList());
	}
}
