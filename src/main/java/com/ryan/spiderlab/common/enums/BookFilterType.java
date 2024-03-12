package com.ryan.spiderlab.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookFilterType {
    HIGH_NUMBER_OF_RENTALS("대여 많은순"),
    LOW_AMOUNT("낮은 가격순"),
    CREATED_AT("최근 등록순");
    private final String status;
}
