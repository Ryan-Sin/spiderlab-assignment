package com.ryan.spiderlab.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ryan.spiderlab.common.enums.ErrorType;
import com.ryan.spiderlab.common.enums.StatusCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonException extends RuntimeException {
    @Builder.Default
    private ErrorType errorType = ErrorType.SYSTEM;
    @Builder.Default
    private String status =  StatusCode.DISASTER.getStatusCode();
    private String clientErrorMessage;
    private String serverErrorMessage;
}
