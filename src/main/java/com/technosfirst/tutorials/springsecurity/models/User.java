package com.technosfirst.tutorials.springsecurity.models;

import com.technosfirst.tutorials.springsecurity.enums.Roles;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private Long id;

    private String email;

    private String password;

    private Roles role;
}
