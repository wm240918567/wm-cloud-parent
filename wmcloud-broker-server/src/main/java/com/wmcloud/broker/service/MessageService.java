package com.wmcloud.broker.service;

import com.wmcloud.broker.entity.Message;

/**
 * message 业务处理
 *
 * @author: 王锰
 * @date: 2019/8/29
 */
public interface MessageService {

    /**
     * 消息初始化
     *
     * @param message 消息对象
     * @return true:初始化成功；false:初始化失败
     */
    boolean msgInit(Message message);


    /**
     * 消息状态变更
     *
     * @param id     消息ID
     * @param status 要变更的状态
     * @return true:操作成功；false:操作失败
     */
    boolean updateStatus(String id, Integer status) throws Exception;

    /**
     * 消息发送
     *
     * @param id 消息ID
     * @return true:操作成功；false:操作失败
     */
    boolean msgSend(String id) throws Exception;


}
