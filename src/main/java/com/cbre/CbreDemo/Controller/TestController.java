package com.cbre.CbreDemo.Controller;

import com.cbre.CbreDemo.dto.Test;
import com.cbre.CbreDemo.service.TestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @PostMapping("/test-api")
    public ResponseEntity<?> testApi(@Valid @RequestBody Test test) {
        testService.ExceptionTest();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
