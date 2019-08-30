package com.wmcloud.broker.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 消息发送策略
 *
 * @author: 王锰
 * @date: 2018/8/29
 */
@Getter
public enum SendStrategy {

    HTTP_JSON,
    MQ,
    ;

}
