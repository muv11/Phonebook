package com.muv.phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is controller class that works with user data modification requests
 * @author muv11
 * @version 1.0 */
@Controller
public class SettingsController {

    @GetMapping("/settings")
    public String getSettings() {
        return "settings";
    }

}
