package com.cbre.CbreDemo.exception.message;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

@Configuration
public class Messages {

    private final MessageSourceAccessor accessor;

    public Messages(MessageSource messageSource) {
        this.accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }

    public String get(final String code, final Locale locale, final Object... args){
        return accessor.getMessage(code, args, locale);
    }

    public String get(final String code, final Locale locale){
        return accessor.getMessage(code, locale);
    }

}
