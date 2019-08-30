package com.wmcloud.broker.entity;

import com.wmcloud.broker.common.SendStrategy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.http.HttpMethod;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


/**
 * 消息对象
 *
 * @author: 王锰
 * @date: 2019/8/29
 */
@Entity(name = "message")
@ApiModel(value = "消息对象", description = "代理对象消息")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Message {

    @Id
    @ApiModelProperty(value = "DB主键", name = "id", dataType = "String", required = true, example = "brokeryyyyMMddHHmmssXXXX")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @NotBlank(message = "发送请求系统不能为空")
    @ApiModelProperty(value = "发送请求系统,spring.application.name", name = "toSys", dataType = "String", required = true)
    @Column(name = "to_sys", length = 100, nullable = false, updatable = false)
    private String toSys;

    @NotBlank(message = "请求接口地址不能为空")
    @ApiModelProperty(value = "请求接口地址,/test/get1", name = "path", dataType = "String", required = true)
    @Column(name = "path", nullable = false, updatable = false)
    private String path;

    @NotNull(message = "请求method不能为空")
    @ApiModelProperty(value = "请求method：post，get......", name = "method", dataType = "String", required = true)
    @Column(name = "method", nullable = false)
    private HttpMethod method;

    @ApiModelProperty(value = "消息体,json格式对象", name = "body", dataType = "String", required = true)
    @Column(name = "body", length = Integer.MAX_VALUE, nullable = false, updatable = false)
    private String body;

    @ApiModelProperty(value = "消息状态,1-init；2-confirm；3-success；4-resend；5-fail；6-discard", name = "status", dataType = "Integer", required = true)
    @Column(name = "status", nullable = false, precision = 2)
    private Integer status;

    @ApiModelProperty(value = "消息发送策略", name = "strategy", dataType = "String")
    @Column(name = "strategy", columnDefinition = "HTTP_JSON")
    private SendStrategy strategy;

    @NotBlank(message = "请求来源系统不能为空")
    @ApiModelProperty(value = "请求来源系统,spring.application.name", name = "fromSys", dataType = "String", required = true)
    @Column(name = "from_sys", length = 100, nullable = false, updatable = false)
    private String fromSys;

    @NotNull(message = "请求接受时间不能为空")
    @ApiModelProperty(value = "请求接收时间", name = "receiveDate", dataType = "date", required = true)
    @Column(name = "receive_date", nullable = false, updatable = false)
    private LocalDateTime receiveDate;
}
