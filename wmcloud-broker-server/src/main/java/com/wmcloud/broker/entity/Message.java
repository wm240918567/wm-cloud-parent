package com.wmcloud.broker.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "message")
@Data
@ApiModel(value = "消息对象",description = "代理对象消息")
public class Message {

    @Id
    @ApiModelProperty(value = "DB主键",name = "id",dataType = "String",required = true,example = "brokeryyyyMMddHHmmssXXXX")
    @Column(name = "id",nullable = false,unique = true)
    private String id;

    @ApiModelProperty(value = "请求发送系统,spring.application.name",name = "toSys",dataType = "String",required = true)
    @Column(name = "to_sys",length = 100,nullable = false,updatable = false)
    private String toSys;

    @ApiModelProperty(value = "请求接口地址,/test/get1",name = "path",dataType = "String",required = true)
    @Column(name = "path",nullable = false,updatable = false)
    private String path;

    @ApiModelProperty(value = "消息体,json格式对象",name = "body",dataType = "String",required = true)
    @Column(name = "body",length = Integer.MAX_VALUE,nullable = false,updatable = false)
    private String body;

    @ApiModelProperty(value = "消息状态,1-init；2-confirm；3-success；4-resend；5-fail；6-discard",name = "status",dataType = "Integer",required = true)
    @Column(name = "status",nullable = false,precision = 2)
    private Integer status;

    @ApiModelProperty(value = "请求来源系统,spring.application.name",name = "fromSys",dataType = "String",required = true)
    @Column(name = "from_sys",length = 100,nullable = false,updatable = false)
    private String fromSys;

    @ApiModelProperty(value = "请求接收时间",name = "receiveDate",dataType = "date",required = true)
    @Column(name = "receive_date",nullable = false,updatable = false)
    private LocalDateTime receiveDate;
}
