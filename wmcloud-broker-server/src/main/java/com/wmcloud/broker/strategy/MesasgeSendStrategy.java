package com.wmcloud.broker.strategy;

import com.wmcloud.broker.entity.Message;

/**
 * 消息发送策略接口
 *
 * @author: 王锰
 * @date: 2019/8/29
 */
public interface MesasgeSendStrategy {

    /**
     * 发送
     *
     * @param message 消息体
     * @return true:发送成功 false:发送失败
     */
    boolean send(Message message);

}
