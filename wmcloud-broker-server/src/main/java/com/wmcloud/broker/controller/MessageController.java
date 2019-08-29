package com.wmcloud.broker.controller;

import com.wmcloud.broker.common.StatusConst;
import com.wmcloud.broker.entity.Message;
import com.wmcloud.broker.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msg")
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;


    @PostMapping("/init")
    public boolean init(@RequestBody Message message){
        return messageService.msginit(message);
    }

    @PutMapping("/send/{id}")
    public boolean send(@PathVariable("id")String id){
        boolean confirmRes = messageService.msgStatus(id, StatusConst.confirm.getCode());
        if(confirmRes) {
            log.info("消息：{}确认成功", id);
            boolean sendRes = messageService.msgSend(id);
            if(sendRes){
                log.info("消息：{}发送成功", id);
            }else {
                log.error("消息：{}发送失败", id);
            }
            return sendRes;
        }else{
            log.error("消息：{}确认失败", id);
            return confirmRes;
        }
    }

}
