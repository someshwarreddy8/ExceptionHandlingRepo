package com.cbre.CbreDemo.util;


import com.cbre.CbreDemo.exception.ApiError;
import com.cbre.CbreDemo.exception.CBREException;
import com.cbre.CbreDemo.exception.messages.MessageArguments;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.Locale;

@Component
public class ExceptionUtil {
    @Autowired
    private MessageSource messageSource;

    public CBREException getCBREException(ApiError apiError, MessageArguments messageArguments) {
        String message = messageSource.getMessage(apiError.getCode(), messageArguments.getArguments(), Locale.getDefault());
        if (message == null) {
            message = "Default error message"; // Provide a default message if the key is not found
        }
        return new CBREException(message, null, apiError, messageArguments.getArguments());
    }

    public CBREException getCBREException(String message, Throwable throwable, ApiError apiError, MessageArguments messageArguments) {
        return new CBREException(message, throwable, apiError, messageArguments.getArguments());
    }

    public CBREException getCBREException(String message, Throwable throwable, MessageArguments messageArguments) {
        return new CBREException(message, throwable,null, messageArguments.getArguments());
    }

    public CBREException getCBREException(Throwable throwable, ApiError apiError, MessageArguments messageArguments) {
        return new CBREException(null, throwable, apiError, messageArguments.getArguments());
    }

    public CBREException getCBREException(Throwable throwable, ApiError apiError) {
        return new CBREException(null, throwable, apiError, null);
    }


    public CBREException getCBREException(ApiError apiError) {
        return new CBREException(null, null, apiError, null);
    }

    public CBREException getCBREException(String message) {
        return new CBREException(message, null, null, null);
    }

    public CBREException getCBREException(String message, Throwable throwable) {
        return new CBREException(message, throwable, null, null);
    }

    public void throwMethodArgumentNotValidException(Method method, int index, BindingResult bindingResult) throws MethodArgumentNotValidException {

        throw new MethodArgumentNotValidException(new MethodParameter(method, 0), bindingResult);

    }

    public void throwMethodArgumentNotValidExceptionForId(Method method, Object object) throws MethodArgumentNotValidException {
        BindingResult bindingResult = new BeanPropertyBindingResult(object, "request");
        FieldError fieldError = new FieldError("request", "id", "id should not be null");
        bindingResult.addError(fieldError);
        throwMethodArgumentNotValidException(method, 0, bindingResult);
    }

    public void throwMethodArgumentNotValidExceptionForVersion(Method method, Object object) throws MethodArgumentNotValidException {
        BindingResult bindingResult = new BeanPropertyBindingResult(object, "request");
        FieldError fieldError = new FieldError("request", "version", "version should not be null");
        bindingResult.addError(fieldError);
        throwMethodArgumentNotValidException(method, 0, bindingResult);
    }

    public String getStackTraceAsString(Throwable throwable) {
        return ExceptionUtils.getStackTrace(throwable);
    }

}
