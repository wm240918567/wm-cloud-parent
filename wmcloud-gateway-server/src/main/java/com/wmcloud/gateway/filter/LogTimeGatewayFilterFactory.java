package com.wmcloud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

/**
 * 自定义拦截器
 * 拦截记录请求从进网关到出网关所用时间
 * AbstractGatewayFilterFactory 无参的一个抽象拦截器
 *
 * @author 王锰
 * @date 16:02 2019/8/1
 */
@Slf4j
public class LogTimeGatewayFilterFactory extends AbstractGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Object config) {
        long start = System.currentTimeMillis();
        return (exchange, chain) -> {
            return chain.filter(exchange.mutate().request(exchange.getRequest()).build()).then(Mono.fromRunnable(() -> {
                long end = System.currentTimeMillis();
                log.info("time:{}", end - start);
            }));
        };
    }
}
