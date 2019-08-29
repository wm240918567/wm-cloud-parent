package com.wmcloud.broker.service.impl;

import com.wmcloud.broker.dao.MessageRepository;
import com.wmcloud.broker.entity.Message;
import com.wmcloud.broker.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository repository;

    @Override
    public boolean msginit(Message message) {
        repository.save(message);
        return true;
    }

    @Override
    public boolean msgStatus(String id, Integer statue) {
        Optional<Message> byId = repository.findById(id);
        Message message = byId.get();
        message.setStatus(statue);
        repository.save(message);
        return true;
    }

    @Override
    public boolean msgSend(String id) {
        return false;
    }
}
