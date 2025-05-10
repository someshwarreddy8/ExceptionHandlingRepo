package com.cbre.CbreDemo.Controller;

import com.cbre.CbreDemo.dto.Test;
import com.cbre.CbreDemo.service.TestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/test-api")
    public ResponseEntity<?> testApi() {
        testService.ExceptionTest();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
