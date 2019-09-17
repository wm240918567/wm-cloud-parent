package com.wmcloud.gateway.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InterfaceDefinition {

    /**
     * 接口名称
     */
    private String name;

    /**
     * 请求完整path路径
     */
    private String uri;

    /**
     * true:支持动态更新  false:不支持动态更新
     */
    private boolean dynamic;

}
