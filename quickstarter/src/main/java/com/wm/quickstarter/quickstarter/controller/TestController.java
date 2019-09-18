package com.wm.quickstarter.quickstarter.controller;

import com.alibaba.fastjson.JSONObject;
import com.wm.quickstarter.quickstarter.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wmframework.exception.BizRuntimeException;
import org.wmframework.idempotent.annotations.Idempotent;
import org.wmframework.idempotent.common.IdempotentStrategy;
import org.wmframework.result.Resp;
import org.wmframework.service.RedisCacheService;

import javax.validation.Valid;

@RestController
public class TestController {

    @Autowired
    private RedisCacheService redisCacheService;

    @PostMapping("/post1")
    @Idempotent(timeout = 30, strategy = IdempotentStrategy.LIST_PARAMETER)
    public Resp<String> post1(@RequestBody @Valid User user) {
        redisCacheService.setRandomExpire(JSONObject.toJSONString(user), "1");
        String s = redisCacheService.get(JSONObject.toJSONString(user));
        return Resp.ok(s);
    }

    @PostMapping("/post2")
    public Resp<String> post2(@RequestBody @Valid User user) {
        throw new BizRuntimeException("post2 test exce");
    }

}
