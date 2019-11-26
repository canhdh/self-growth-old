package com.selfgrowth.composite.keyresult;

import com.selfgrowth.model.util.DebugLog;
import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@ComponentScan({"com.selfgrowth.composite.keyresult.service", "com.selfgrowth.model.util"})
public class KeyResultServiceCompositeApplication {

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        ConfigurableApplicationContext ctx = SpringApplication.run(KeyResultServiceCompositeApplication.class, args);

        DebugLog.logMessage("CustomerCompositeServiceApplication Connected to RabbitMQ at: " + ctx.getEnvironment().getProperty("spring.rabbitmq.host"));
    }
}
