package com.wmcloud.broker.controller;

import com.wmcloud.broker.common.StatusConst;
import com.wmcloud.broker.entity.Message;
import com.wmcloud.broker.service.MessageService;
import com.wmcloud.broker.util.MessageIdUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/msg")
@Slf4j
@Api("消息接口")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageIdUtils messageIdUtils;

    @GetMapping("/get1/{id}")
    @ApiOperation(value = "/get1/{id}方法")
    public boolean get1(@PathVariable("id")String id){
        throw new RuntimeException("id:" + id);
    }


    @PostMapping("/init")
    @ApiOperation(value = "/init方法")
    public boolean init(@RequestBody Message message) {
        message.setId(messageIdUtils.initId());
        message.setStatus(StatusConst.init.getCode());
        return messageService.msgInit(message);
    }

    @PutMapping("/send/{id}")
    public boolean send(@PathVariable("id") String id) throws Exception {
        boolean confirmRes = messageService.updateStatus(id, StatusConst.confirm.getCode());
        if (confirmRes) {
            log.info("消息：{}确认成功", id);
            boolean sendRes = messageService.msgSend(id);
            if (sendRes) {
                log.info("消息：{}发送成功", id);
            } else {
                log.error("消息：{}发送失败", id);
            }
            return sendRes;
        } else {
            log.error("消息：{}确认失败", id);
            return confirmRes;
        }
    }

}
