package com.k001.selfgorwth.oauth;

import com.selfgrowth.model.role.Role;
import com.selfgrowth.model.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableEurekaClient
@EntityScan(basePackageClasses = {
        OAuthServerApplication.class,
        Jsr310JpaConverters.class,
        User.class,
        Role.class
})
public class OAuthServerApplication {

    @PostConstruct
    void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(OAuthServerApplication.class, args);
    }
}
