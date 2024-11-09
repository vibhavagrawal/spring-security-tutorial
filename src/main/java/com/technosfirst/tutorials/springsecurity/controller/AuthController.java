package com.technosfirst.tutorials.springsecurity.controller;

import com.technosfirst.tutorials.springsecurity.models.LoginRequest;
import com.technosfirst.tutorials.springsecurity.models.LoginResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @RequestMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        return LoginResponse.builder()
                .authToken("auth-token" + System.currentTimeMillis())
                .build();
    }
}
