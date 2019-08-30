package com.wmcloud.broker.service.impl;

import com.wmcloud.broker.dao.MessageRepository;
import com.wmcloud.broker.entity.Message;
import com.wmcloud.broker.service.MessageService;
import com.wmcloud.broker.strategy.MessageSendStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository repository;

    @Autowired
    private MessageSendStrategyContext messageSendStrategyContext;

    @Override
    public boolean msgInit(Message message) {
        repository.save(message);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public boolean updateStatus(String id, Integer statue) throws Exception {
        Optional<Message> messageOptional = repository.findById(id);
        Message message = messageOptional.map(m -> {
            m.setStatus(statue);
            return m;
        }).orElseThrow(() -> new Exception("消息不存在" + id));
        repository.save(message);
        return true;
    }

    @Override
    public boolean msgSend(String id) throws Exception {
        Optional<Message> messageOptional = repository.findById(id);
        return messageOptional.map(m -> messageSendStrategyContext.accept(m)).orElseThrow(() ->
                new Exception("消息不存在"));
    }
}
