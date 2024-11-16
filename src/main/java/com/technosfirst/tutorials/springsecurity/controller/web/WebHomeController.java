package com.technosfirst.tutorials.springsecurity.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebHomeController {

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/secured")
    public String secured() {
        return "secured";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
