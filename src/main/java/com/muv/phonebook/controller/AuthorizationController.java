package com.muv.phonebook.controller;

import com.muv.phonebook.service.AuthorizationService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This is controller class that works with authorization requests
 * @author muv11
 * @version 1.3 */
@Controller
public class AuthorizationController {

    private final AuthorizationService authorizationService;
    private final PhonebookController phonebookController;

    public AuthorizationController(AuthorizationService authorizationService, PhonebookController phonebookController) {
        this.authorizationService = authorizationService;
        this.phonebookController = phonebookController;
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
                return phonebookController.getPhonebook(model);
            }
            model.addAttribute("error", "Пользователя с таким логином и паролем не существует");
            return "authorization";
        } catch (EmptyResultDataAccessException exc) {
            model.addAttribute("error", "Пользователя с таким логином и паролем не существует");
            return "authorization";
        }
    }

}
