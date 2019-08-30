package com.wmcloud.broker.strategy;


import com.wmcloud.broker.entity.Message;
import org.springframework.stereotype.Component;

/**
 * 消息发送策略转换器
 *
 * @author: 王锰
 * @date: 2019/8/29
 */
@Component
public class MessageSendStrategyContext extends AbstractStrategyContext<MesasgeSendStrategy> {


    public boolean accept(Message message) {
        return getStrategy(message.getStrategy().name()).send(message);
    }

}
