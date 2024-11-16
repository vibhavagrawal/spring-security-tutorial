package com.technosfirst.tutorials.springsecurity.controller.rest;

import com.technosfirst.tutorials.springsecurity.config.WithAdminUser;
import com.technosfirst.tutorials.springsecurity.config.WithMockUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestHelloControllerTest {

    @Autowired
    private MockMvc api;

    @Test
    void anyoneCanViewPublicHelloEndpoint() throws Exception {
        api.perform(get("/api/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("Hello")));
    }

    @Test
    void notLoggedIn_shouldNotSeeSecuredEndpoint() throws Exception {
        api.perform(get("/api/secured"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void loggedIn_shouldSeeSecuredEndpoint() throws Exception {
        api.perform(get("/api/secured"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("Your user id is 1")));
    }

    @Test
    void notLoggedIn_shouldNotSeeAdminEndpoint() throws Exception {
        api.perform(get("/api/admin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void simpleUser_shouldNotSeeAdminEndpoint() throws Exception {
        api.perform(get("/api/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithAdminUser
    void adminUser_shouldSeeAdminEndpoint() throws Exception {
        api.perform(get("/api/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("Your user id is 1. You see this as you are an admin")));
    }

    @Test
    void notLoggedIn_shouldNotSeeAnotherAdminEndpoint() throws Exception {
        api.perform(get("/api/anotheradmin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void simpleUser_shouldNotSeeAnotherAdminEndpoint() throws Exception {
        api.perform(get("/api/anotheradmin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithAdminUser
    void adminUser_shouldSeeAnotherAdminEndpoint() throws Exception {
        api.perform(get("/api/anotheradmin"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("Your user id is 1. You see this another endpoint only if you are an admin")));
    }
}