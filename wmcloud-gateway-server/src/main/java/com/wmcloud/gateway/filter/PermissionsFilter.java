package com.wmcloud.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.wmcloud.gateway.entity.InterfaceDefinition;
import com.wmcloud.gateway.entity.PermissionRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.wmframework.result.Resp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class PermissionsFilter implements GlobalFilter, Ordered {

    public static Map<String, PermissionRegister> map = new ConcurrentHashMap<>();

    @Value(value = "${spring.application.name}")
    private String oneself;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (null == map || map.size() == 0) {
            log.warn("permissions register is empty,skip");
            return chain.filter(exchange);
        }
        String pathWithServiceName = getPathWithServiceName(exchange);
        String[] split = pathWithServiceName.split("/");
        String serviceName = getServiceName(pathWithServiceName, split);
        if (!StringUtils.isEmpty(serviceName)) {
            if (serviceName.equals(oneself)) {
                return chain.filter(exchange);
            }
            //不存在请求的服务名不过滤
            if (null == map.get(serviceName)) {
                log.warn("permissionRegister don't have service:{}", serviceName);
                return chain.filter(exchange);
            }
        }
        String path = getPath(pathWithServiceName, split);
        PermissionRegister permissionRegister = map.get(serviceName);
        Map<String, InterfaceDefinition> permissionsMap = permissionRegister.getPermissionsMap();
        if (null == permissionsMap) {
            log.error("permissions register error,service:{}", serviceName);
            return chain.filter(exchange);
        }
        InterfaceDefinition interfaceDefinition = permissionsMap.get(path);
        if (null != interfaceDefinition) {
            String res = "该请求被过滤";
            log.info(res);
            return exchange.getResponse().writeWith(Flux.just(exchange.getResponse().
                    bufferFactory().wrap(JSONObject.toJSONString(Resp.badReq(res)).getBytes())));
        }
        return chain.filter(exchange);
    }

    private String getPath(String pathWithServiceName, String[] split) {
        StringBuilder pathWithoutServiceName = new StringBuilder("/");
        for (int i = pathWithServiceName.startsWith("/") ? 2 : 1; i < split.length; i++) {
            pathWithoutServiceName.append(split[i]).append("/");
        }
        return pathWithoutServiceName.substring(0, pathWithoutServiceName.length() - 1);
    }

    private String getServiceName(String pathWithServiceName, String[] split) {
        return pathWithServiceName.startsWith("/") ? split[1] : split[0];
    }

    private String getPathWithServiceName(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        RequestPath requestPath = request.getPath();
        PathContainer pathContainer = requestPath.pathWithinApplication();
        return pathContainer.value();
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

}
