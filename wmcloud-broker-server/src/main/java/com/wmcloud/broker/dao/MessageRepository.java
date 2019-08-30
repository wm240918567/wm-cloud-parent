package com.wmcloud.broker.dao;

import com.wmcloud.broker.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * message 持久化
 *
 * @author: 王锰
 * @date: 2019/8/29
 */
@Repository
public interface MessageRepository extends JpaRepository<Message,String> {
}
