package com.wmcloud.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
@Slf4j
public class WmcloudAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmcloudAdminServerApplication.class, args);
        log.info("====================admin-server start!====================");
    }

}
