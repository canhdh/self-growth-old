package com.selfgrowth.core.okrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.selfgrowth.core.okrs", "com.selfgrowth.util"})
public class OKRServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OKRServiceApplication.class, args);
    }
}
