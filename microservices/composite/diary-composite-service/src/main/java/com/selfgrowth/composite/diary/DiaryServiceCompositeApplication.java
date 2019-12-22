package com.selfgrowth.composite.diary;

import com.selfgrowth.model.util.DebugLog;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@ComponentScan({"com.selfgrowth.model.util","com.selfgrowth.composite.diary.service"})
@EnableCaching
public class DiaryServiceCompositeApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        ConfigurableApplicationContext context = SpringApplication.run(DiaryServiceCompositeApplication.class, args);

        DebugLog.logMessage("DiaryCompositeService connected to RabbitMQ at: " + context.getEnvironment().getProperty("spring.rabbitmq.host"));
    }

    @Bean
    public Docket swaggerPersonApi10(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.selfgrowth.composite.diary.service"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().version("0.1").title("Diary Composite Service").description("Documentation Diary API v0.1").build());
    }
}
