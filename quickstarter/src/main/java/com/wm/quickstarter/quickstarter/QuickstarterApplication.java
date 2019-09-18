package com.wm.quickstarter.quickstarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wm","org.wmframework"})
@EnableDiscoveryClient
public class QuickstarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickstarterApplication.class, args);
    }

}
