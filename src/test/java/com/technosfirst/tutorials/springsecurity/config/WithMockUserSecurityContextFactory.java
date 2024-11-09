package com.technosfirst.tutorials.springsecurity.config;

import com.technosfirst.tutorials.springsecurity.security.UserPrincipal;
import com.technosfirst.tutorials.springsecurity.security.UserPrincipalAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.Arrays;

public class WithMockUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockUser annotation) {
        var authorities = Arrays.stream(annotation.authorities())
                .map(SimpleGrantedAuthority::new)
                .toList();
        var principal = UserPrincipal.builder()
                .userId(annotation.userId())
                .email("fake@email.com")
                .authorities(authorities)
                .build();

        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(new UserPrincipalAuthenticationToken(principal));
        return context;
    }
}
