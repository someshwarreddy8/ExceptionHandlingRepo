package com.cbre.CbreDemo.exception;

import com.cbre.CbreDemo.exception.message.Messages;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandling {
    @Autowired
    private Messages messages;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CBREException.class)
    public ResponseEntity<Map<String, Object>> handleCBREException(CBREException cbreException) {
        Map<String, Object> errorResponse = new HashMap<>();
        ApiError apiError = cbreException.getApiError();

        if (apiError == null) {
            apiError = ApiError.UNHANDLED_EXCEPTION;
        }

        Object[] messageArguments = cbreException.getMessageArguments();
        String message = messages.get(apiError.getCode(), Locale.ENGLISH, messageArguments);
        if (StringUtils.isNotBlank(cbreException.getMessage())) {
            message = cbreException.getMessage().isBlank() ? message : cbreException.getMessage();
        }

        errorResponse.put("error", cbreException.getApiError().name());
        errorResponse.put("message", message);
        errorResponse.put("details", cbreException.getMessageArguments());
        errorResponse.put("TimeStamp", ZonedDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
