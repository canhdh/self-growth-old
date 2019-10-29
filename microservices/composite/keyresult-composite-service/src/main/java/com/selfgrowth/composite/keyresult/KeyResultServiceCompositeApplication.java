package com.selfgrowth.composite.keyresult;

import com.selfgrowth.model.util.DebugLog;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableResourceServer
//@EnableSwagger2
//@EnableAspectJAutoProxy(proxyTargetClass = true)
@EntityScan("com.selfgrowth.model.keyResult")
@ComponentScan({"com.selfgrowth.composite.keyresult", "com.selfgrowth.util"})
public class KeyResultServiceCompositeApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(KeyResultServiceCompositeApplication.class, args);

        DebugLog.logMessage("CustomerCompositeServiceApplication Connected to RabbitMQ at: " + ctx.getEnvironment().getProperty("spring.rabbitmq.host"));
    }

//    @Bean
//    public Docket swaggerPersonApi10() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.selfgrowth.composite.keyresult.KeyResultCompositeService"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(new ApiInfoBuilder().version("1.0").title("User Service API").description("Documentation User API v1.0").build());
//    }
}
