package com.muv.phonebook.controller;

import com.muv.phonebook.model.Contact;
import com.muv.phonebook.service.LoggedUserManagementService;
import com.muv.phonebook.service.PhonebookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * This is controller class that works with the requests of the main app's page
 * @author muv11
 * @version 1.3 */
@Controller
public class PhonebookController {

    private final PhonebookService phonebookService;
    private final LoggedUserManagementService userManagementService;

    public PhonebookController(PhonebookService phonebookService, LoggedUserManagementService userManagementService) {
        this.phonebookService = phonebookService;
        this.userManagementService = userManagementService;
    }

    @GetMapping("/phonebook")
    public String getPhonebook(Model model) {
        if (userManagementService.getLogin() == null) {
            return "authorization";
        }
        List<Contact> contacts = phonebookService.getContactList(userManagementService.getLogin());
        model.addAttribute("contactsList", contacts);
        model.addAttribute("space", " ");
        return "phonebook";
    }

    @GetMapping("/redirectToSettings")
    public String redirectSettings() {
        return "redirect:settings";
    }

    @GetMapping("/redirectToAdd")
    public String redirectAdd() {
        return "redirect:add";
    }

    @GetMapping("/redirectToChange")
    public String redirectChange() {
        return "redirect:change";
    }

}
