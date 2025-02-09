package com.cbre.CbreDemo.service;

import com.cbre.CbreDemo.exception.ApiError;
import com.cbre.CbreDemo.exception.messages.MessageArguments;
import com.cbre.CbreDemo.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private ExceptionUtil exceptionUtil;

    public void ExceptionTest() {

        try {
            throw exceptionUtil.getCBREException(ApiError.UNHANDLED_EXCEPTION, new MessageArguments("XYZZZ", "IOS OR ANDROID"));
        } catch (Exception e) {

        }
    }

}
