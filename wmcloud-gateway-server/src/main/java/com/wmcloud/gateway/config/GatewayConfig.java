package com.wmcloud.gateway.config;

import com.wmcloud.gateway.filter.LogTimeGatewayFilterFactory;
import com.wmcloud.gateway.filter.SwaggerHeaderFilter;
import com.wmcloud.gateway.filter.TraceIdFilter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 拦截器初始化配置类
 *
 * @author: 王锰
 * @date: 2019/9/2
 */
@Configuration
public class GatewayConfig {

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

    /**
     * swagger2 微服务api统一管理拦截器
     *
     * @return SwaggerHeaderFilter拦截器
     */
    @Bean
    public SwaggerHeaderFilter swaggerHeaderFilter() {
        return new SwaggerHeaderFilter();
    }

    /**
     * 地址限流拦截器
     *
     * @return KeyResolver
     */
    @Bean
    @Primary
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostString());
    }

}
