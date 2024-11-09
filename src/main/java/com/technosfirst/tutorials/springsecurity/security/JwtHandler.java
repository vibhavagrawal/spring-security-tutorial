package com.technosfirst.tutorials.springsecurity.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtHandler {

    private final JwtProperties properties;

    public String generateToken(long userId, String email, List<String> roles) {
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .expiration(Date.from(Instant.now().plus(Duration.of(1, ChronoUnit.HOURS))))
                .claim("e", email)
                .claim("r", roles)
                .signWith(Keys.hmacShaKeyFor(properties.getSecretKey().getBytes()))
                .compact();
    }

    public Jws<Claims> extractToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(properties.getSecretKey().getBytes()))
                .build()
                .parseSignedClaims(token);
    }
}
