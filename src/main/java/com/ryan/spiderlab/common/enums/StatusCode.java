package com.ryan.spiderlab.common.enums;

import lombok.Getter;

@Getter
public enum StatusCode {
    SUCCESS("success"),
    FAIL("fail"),
    DISASTER("disaster");

    private String statusCode;

    StatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
