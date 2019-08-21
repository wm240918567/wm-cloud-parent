package com.wmcloud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hystrix的熔断操作请求类
 * 测试用
 *
 * @author 王锰
 * @date 18:15 2019/8/1
 */
@RestController
public class FailBackController {

    @GetMapping("/fallback")
    public String wmtest1() {
        return "wm-test1-fall-back";
    }

}
