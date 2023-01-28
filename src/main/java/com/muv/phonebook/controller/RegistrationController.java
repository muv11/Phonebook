package com.muv.phonebook.controller;

import com.muv.phonebook.model.User;
import com.muv.phonebook.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This is controller class that works with registration requests
 * @author muv11
 * @version 1.1 */
@Controller
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String email,
            Model model
    ) {
        User user = new User(login, password, email);
        if (registrationService.registerUser(user)) {
            return "authorization";
        }
        model.addAttribute("error", "Неверно заполнены поля. Заполните еще раз");
        return "registration";
    }

}