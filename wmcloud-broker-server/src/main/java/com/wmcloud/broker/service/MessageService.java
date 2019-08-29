package com.wmcloud.broker.service;


import com.wmcloud.broker.entity.Message;

public interface MessageService {

    /**
     * 消息初始化
     *
     * @param message 消息对象
     * @return true:初始化成功；false:初始化失败
     */
    boolean msginit(Message message);


    /**
     * 消息状态变更
     *
     * @param id     消息ID
     * @param statue 要变更的状态
     * @return true:操作成功；false:操作失败
     */
    boolean msgStatus(String id, Integer statue);

    /**
     * 消息发送
     *
     * @param id 消息ID
     * @return true:操作成功；false:操作失败
     */
    boolean msgSend(String id);



}
