package com.technosfirst.tutorials.springsecurity.controller;

import com.technosfirst.tutorials.springsecurity.models.LoginRequest;
import com.technosfirst.tutorials.springsecurity.models.LoginResponse;
import com.technosfirst.tutorials.springsecurity.security.JwtHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtHandler jwtHandler;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
        String token = jwtHandler.generateToken(1, loginRequest.getEmail(), List.of("USER"));
        return LoginResponse.builder()
                .authToken(token)
                .build();
    }
}
