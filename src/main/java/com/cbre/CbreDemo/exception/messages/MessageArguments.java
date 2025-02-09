package com.cbre.CbreDemo.exception.messages;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageArguments {
    private final Object[] arguments;

    public MessageArguments(Object... args) {
        this.arguments = args;
    }
}
