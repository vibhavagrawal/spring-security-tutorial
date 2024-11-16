package com.technosfirst.tutorials.springsecurity.controller.rest;

import com.technosfirst.tutorials.springsecurity.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestHelloController {

    @Value("${application.env}")
    private String environment;

    @GetMapping("/")
    public String hello() {
        return "Hello, World! Welcome to " + environment + " environment!";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {
        return String.format("Hello, %s! Your user id is %s", principal.getEmail(), principal.getUserId());
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal) {
        return String.format("Hello, %s! Your user id is %s. You see this as you are an admin!", principal.getEmail(), principal.getUserId());
    }

    @GetMapping("/anotheradmin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminWithPreAuthMethodTag(@AuthenticationPrincipal UserPrincipal principal) {
        return String.format("Hello, %s! Your user id is %s. You see this another endpoint only if you are an admin!", principal.getEmail(), principal.getUserId());
    }
}
