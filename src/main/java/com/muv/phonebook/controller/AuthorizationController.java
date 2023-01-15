package com.muv.phonebook.controller;

import com.muv.phonebook.service.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationController {

    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping("/")
    public String getAuthorization() {
        return "authorization";
    }

    @GetMapping("/redirectToRegistration")
    public String redirectRegistration() {
        return "redirect:registration";
    }

    @PostMapping("/")
    public String postAuthorization(
            @RequestParam String login,
            @RequestParam String password
    ) {
        if (authorizationService.isUserCorrect(login, password)) {
            return "phonebook";
        }
        return "errors/authorizationError";
    }

}
