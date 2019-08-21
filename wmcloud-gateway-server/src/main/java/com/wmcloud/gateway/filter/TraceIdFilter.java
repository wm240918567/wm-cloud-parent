package com.wmcloud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;


/**
 * 日志链路跟踪过滤器
 * web请求走网关进入，生成该请求的唯一ID
 * 通过requestHeader发送到下游微服务中
 * 进行日志的链路跟踪
 * 配合ELK类似的日志整合，可以通过traceId找到该请求相关所有日志
 *
 * @author 王锰
 * @date 15:55 2019/8/13
 */
@Slf4j
public class TraceIdFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String traceId = UUID.randomUUID().toString().replaceAll("-", "");
        MDC.put("traceId", traceId);
        ServerHttpRequest request = exchange.getRequest();
        RequestPath path = request.getPath();
        PathContainer pathContainer = path.pathWithinApplication();

        log.info("请求path：{}", pathContainer.value());
        exchange.getRequest().mutate().header("traceId", traceId);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
