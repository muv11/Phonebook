package com.muv.phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorizationController {

    @GetMapping("/")
    public String getAuthorization() {
        return "authorization";
    }

    @GetMapping("/redirectToRegistration")
    public String redirectRegistration() {
        return "redirect:registration";
    }


}
