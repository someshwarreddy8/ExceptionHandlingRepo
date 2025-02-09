package com.cbre.CbreDemo.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ApiError {
    UNHANDLED_EXCEPTION("vm-000", HttpStatus.INTERNAL_SERVER_ERROR.value());

    private final String code;
    private final int httpStatusCode;

    ApiError(String code, int httpStatusCode) {
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }
}
