package com.wmcloud.gateway.controller;

import com.wmcloud.gateway.entity.PermissionRegister;
import com.wmcloud.gateway.filter.PermissionsFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PermissionRegisterController {

    @PostMapping("/register")
    public String register(@RequestBody PermissionRegister table) {
        log.info("table:{}", table);
        PermissionsFilter.map.put(table.getServiceName(), table);
        return "OK";
    }

}
