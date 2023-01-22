package com.muv.phonebook.controller;

import com.muv.phonebook.service.AuthorizationService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
            @RequestParam String password,
            Model model
    ) {
        try {
            if (authorizationService.isUserCorrect(login, password)) {
                return "phonebook";
            }
            model.addAttribute("error", "Пользователя с таким логином и паролем не существует");
            return "authorization";
        } catch (EmptyResultDataAccessException exc) {
            model.addAttribute("error", "Пользователя с таким логином и паролем не существует");
            return "authorization";
        }
    }

}
