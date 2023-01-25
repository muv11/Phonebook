package com.muv.phonebook.controller;

import com.muv.phonebook.service.PhonebookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhonebookController {

    private final PhonebookService phonebookService;

    public PhonebookController(PhonebookService phonebookService) {
        this.phonebookService = phonebookService;
    }

    @GetMapping("/phonebook")
    public String getPhonebook(Model model) {

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
