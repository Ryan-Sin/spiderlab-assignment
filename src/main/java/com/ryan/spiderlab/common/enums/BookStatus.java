package com.ryan.spiderlab.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookStatus {
    AVAILABLE("대출가능"),
    LOAN("대출하다.");
    private final String status;

}
