package com.wmcloud.gateway.filter;

import com.wmcloud.gateway.entity.InterfaceDefinition;
import com.wmcloud.gateway.entity.PermissionRegister;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class PermissionsFilter implements GlobalFilter, Ordered {

    public static Map<String, PermissionRegister> map = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        RequestPath path = request.getPath();
        PathContainer pathContainer = path.pathWithinApplication();
        log.info("请求path：{}", pathContainer.value());
        String value = pathContainer.value();
        String[] split = value.split("/");
        String serviceName = value.startsWith("/") ? split[1] : split[0];
        PermissionRegister permissionRegister = map.get(serviceName);
        Map<String, InterfaceDefinition> permissionsMap = permissionRegister.getPermissionsMap();
        boolean endsWith = value.endsWith("/");
        String pathStr = null;
        if (endsWith) {
            pathStr = value.substring(0, value.length() - 1);
        }
        InterfaceDefinition interfaceDefinition = permissionsMap.get(pathStr);
        if (null != interfaceDefinition) {
            String uri = interfaceDefinition.getUri();
            if (StringUtils.isNotEmpty(pathStr) && pathStr.endsWith(pathStr)) {
                log.info("该请求被过滤");
                return null;
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

}
