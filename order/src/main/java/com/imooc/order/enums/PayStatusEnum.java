package com.imooc.order.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {

    WAIT(0,"新订单"),
    SUCCESS(1,"完结"),
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
