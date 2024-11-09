package com.technosfirst.tutorials.springsecurity.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties("security.jwt")
public class JwtProperties {
    private String secretKey;
}
