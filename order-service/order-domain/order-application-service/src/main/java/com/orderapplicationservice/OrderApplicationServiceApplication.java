package com.orderapplicationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.orderdomaincore.*"})
public class OrderApplicationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplicationServiceApplication.class, args);
    }

}
