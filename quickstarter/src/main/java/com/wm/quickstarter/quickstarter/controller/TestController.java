package com.wm.quickstarter.quickstarter.controller;

import com.alibaba.fastjson.JSONObject;
import com.wm.quickstarter.quickstarter.dto.UserDto;
import com.wm.quickstarter.quickstarter.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wmframework.exception.BizExceptionInfo;
import org.wmframework.exception.BizRuntimeException;
import org.wmframework.permissions.Permissions;
import org.wmframework.permissions.Role;
import org.wmframework.result.Resp;
import org.wmframework.service.RedisCacheService;
import org.wmframework.util.BeanUtils;
import org.wmframework.util.HttpContextUtils;

import javax.validation.Valid;

@RestController
@Api(value = "测试控制器")
@Slf4j
public class TestController {

    @Autowired
    private RedisCacheService redisCacheService;

    @PostMapping("/post1")
    @ApiOperation(value = "post1接口")
    @Permissions(name = "post1接口",role = Role.SUPER_ADMIN)
//    @Idempotent(timeout = 30, strategy = IdempotentStrategy.LIST_PARAMETER)
    public Resp<String> post1(@RequestBody @Valid UserDto userDto) {
        User user = BeanUtils.convertIgnoreNull(userDto, new User());
        redisCacheService.setRandomExpire("user", JSONObject.toJSONString(user));
        String res = redisCacheService.get("user");
        return Resp.ok(res);
    }

    @PostMapping("/post2")
    @ApiOperation(value = "post2接口")
    public Resp<String> post2(@RequestBody @Valid UserDto userDto) {
        String clientIp = HttpContextUtils.getClientIp(HttpContextUtils.getHttpServletRequest());
        log.info("clientIp:{}", clientIp);
        throw new BizRuntimeException(BizExceptionInfo.builder().errCode(1).errMsg("ppppp").build());
    }

}
