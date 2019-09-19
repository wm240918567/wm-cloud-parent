package com.wm.quickstarter.quickstarter;

import com.wm.quickstarter.quickstarter.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.wmframework.util.DateUtils;
import org.wmframework.util.ReflectionUtils;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.naming.Name;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@ComponentScan(basePackages = {"com.wm","org.wmframework"})
@EnableDiscoveryClient
public class QuickstarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickstarterApplication.class, args);
    }

}
