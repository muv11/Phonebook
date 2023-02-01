package com.muv.phonebook.controller;

import com.muv.phonebook.model.User;
import com.muv.phonebook.service.LoggedUserManagementService;
import com.muv.phonebook.service.SettingsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This is controller class that works with user data modification requests
 * @author muv11
 * @version 2.0 */
@Controller
public class SettingsController {

    private final SettingsService settingsService;
    private final LoggedUserManagementService userManagementService;

    public SettingsController(SettingsService settingsService, LoggedUserManagementService userManagementService) {
        this.settingsService = settingsService;
        this.userManagementService = userManagementService;
    }

    @GetMapping("/settings")
    public String getSettings(Model model) {
        if (userManagementService.getLogin() == null) {
            return "authorization";
        }
        model.addAttribute("login", settingsService.getCurrentLogin());
        model.addAttribute("password", settingsService.getCurrentPassword());
        model.addAttribute("email", settingsService.getCurrentEmail());
        return "settings";
    }

    @PostMapping("/settings")
    public String postSettings(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String email,
            Model model
    ) {
        if (settingsService.updateUser(new User(login, password, email))) {
            return "phonebook";
        }
        model.addAttribute("error", "Неверно заполнены поля. Заполните еще раз");
        return "settings";
    }

}
