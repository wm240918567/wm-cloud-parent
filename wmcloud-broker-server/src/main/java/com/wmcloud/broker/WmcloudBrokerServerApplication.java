package com.wmcloud.broker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WmcloudBrokerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmcloudBrokerServerApplication.class, args);
    }

}
