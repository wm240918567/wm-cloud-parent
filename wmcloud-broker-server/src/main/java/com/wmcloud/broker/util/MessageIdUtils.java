package com.wmcloud.broker.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.wmframework.tools.DateUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class MessageIdUtils {

    public static final String BROKER_PREFIX = "broker";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String initId() {
        String redisKey = DateUtils.format(new Date(), "yyyyMMddHHmmss");
        Long redisValue = redisTemplate.opsForValue().increment(redisKey);
        String postfix = StringUtils.leftPad(redisValue + "", 4, "0");
        Boolean expireRes = Boolean.FALSE;
        try {
            expireRes = redisTemplate.expire(redisKey, 5, TimeUnit.SECONDS);
        } catch (Exception ignored) {
        }
        if (null == expireRes || !expireRes) {
            log.error("设置超时时间失败，key：{}", redisKey);
            //TODO 需要一个记录设置超时时间失败的功能，记录设置失败的key。通过后续补偿机制处理多余的key
        }
        return BROKER_PREFIX + redisKey + postfix;
    }

}
