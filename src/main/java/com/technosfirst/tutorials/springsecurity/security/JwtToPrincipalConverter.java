package com.technosfirst.tutorials.springsecurity.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtToPrincipalConverter {
    public UserPrincipal convert(Jws<Claims> claimsJwt) {
        return UserPrincipal.builder()
                .userId(Long.parseLong(claimsJwt.getPayload().getSubject()))
                .email(claimsJwt.getPayload().get("e", String.class))
                .authorities(getAuthoritiesFromClaim(claimsJwt))
                .build();
    }

    private List<SimpleGrantedAuthority> getAuthoritiesFromClaim(Jws<Claims> claimsJwt) {
        List<?> claims = claimsJwt.getPayload().get("r", List.class);
        if (claims == null || claims.isEmpty()) return List.of();
        return claims.stream()
                .map(String::valueOf)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
