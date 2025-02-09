package com.cbre.CbreDemo.exception;

import lombok.Getter;

@Getter
public class CBREException extends RuntimeException {
    private final ApiError apiError;
    private final String message;
    private final Object[] messageArguments;

    public CBREException(String message, Throwable cause, ApiError apiError, Object[] messageArguments) {
        super(message, cause);
        this.apiError = apiError;
        this.message = message;
        this.messageArguments = messageArguments;
    }
}
