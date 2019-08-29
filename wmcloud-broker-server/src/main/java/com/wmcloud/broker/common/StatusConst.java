package com.wmcloud.broker.common;

import lombok.Getter;

@Getter
public enum StatusConst {

    init(1),

    confirm(2),

    success(3),

    resend(4),

    fail(5),

    discard(6),
    ;

    StatusConst(int code) {
        this.code = code;
    }

    private int code;

}
