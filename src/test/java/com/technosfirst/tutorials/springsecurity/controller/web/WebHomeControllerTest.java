package com.technosfirst.tutorials.springsecurity.controller.web;

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
class WebHomeControllerTest {

    @Autowired
    private MockMvc web;

    @Test
    void anyoneCanViewPublicHomeEndpoint() throws Exception {
        web.perform(get("/web/home"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("Welcome")));
    }

    @Test
    void notLoggedIn_shouldNotSeeSecuredEndpoint() throws Exception {
        web.perform(get("/web/secured"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void loggedIn_shouldSeeSecuredEndpoint() throws Exception {
        web.perform(get("/web/secured"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("Your user id is 1")));
    }

    @Test
    void notLoggedIn_shouldNotSeeAdminEndpoint() throws Exception {
        web.perform(get("/web/admin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void simpleUser_shouldNotSeeAdminEndpoint() throws Exception {
        web.perform(get("/web/admin"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithAdminUser
    void adminUser_shouldSeeAdminEndpoint() throws Exception {
        web.perform(get("/web/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("Your user id is 1. You see this as you are an admin")));
    }
}