package com.ryan.spiderlab.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessResponse<T>{
    private Common common;
    @Schema(description = "클라이언트의 요청 된 결과 데이터르 반환")
    private T data;

    public static <T> SuccessResponse<T> setSuccessResponse(T data){
        return SuccessResponse.<T>builder()
                .common(Common.builder().build())
                .data(data)
                .build();
    }
}

