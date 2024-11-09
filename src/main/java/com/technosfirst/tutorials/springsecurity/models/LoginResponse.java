package com.technosfirst.tutorials.springsecurity.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private final String authToken;
}
