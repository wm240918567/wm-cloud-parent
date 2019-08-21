package com.wmcloud.zipkin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
@Slf4j
public class WmcloudZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmcloudZipkinServerApplication.class, args);
        log.info("====================zipkin-server start!====================");
    }

}
