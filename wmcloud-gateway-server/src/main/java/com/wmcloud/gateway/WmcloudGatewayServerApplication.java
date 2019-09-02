package com.wmcloud.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class WmcloudGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmcloudGatewayServerApplication.class, args);
        log.info("====================gateway-server start!====================");
    }

}
