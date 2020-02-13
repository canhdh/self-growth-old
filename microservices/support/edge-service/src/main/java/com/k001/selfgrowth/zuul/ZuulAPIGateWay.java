package com.k001.selfgrowth.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableZuulProxy
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ZuulAPIGateWay
{

	public static void main(String[] args) { SpringApplication.run(ZuulAPIGateWay.class, args); }

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
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
