package org.example.token.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/users/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users/join")
    public String signUp() {
        return "sign-up";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

}
