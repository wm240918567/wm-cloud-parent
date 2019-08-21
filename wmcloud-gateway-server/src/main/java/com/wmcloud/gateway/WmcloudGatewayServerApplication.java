package com.wmcloud.gateway;

import com.wmcloud.gateway.filter.LogTimeGatewayFilterFactory;
import com.wmcloud.gateway.filter.TraceIdFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Objects;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class WmcloudGatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WmcloudGatewayServerApplication.class, args);
        log.info("====================gateway-server start!====================");
    }

    /**
     * 自定义拦截器需要注入到spring中
     *
     * @return LogTimeGatewayFilterFactory 拦截器
     */
    @Bean
    public LogTimeGatewayFilterFactory tokenValidateGatewayFilterFactory() {
        return new LogTimeGatewayFilterFactory();
    }

    /**
     * 启动日志链路跟踪拦截器
     *
     * @return TraceIdFilter 日志traceId拦截器
     */
    @Bean
    public TraceIdFilter traceIdFilter() {
        return new TraceIdFilter();
    }

    /*地址限流*/
    @Bean
    @Primary
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostString());
    }


}
