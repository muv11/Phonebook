package com.muv.phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PhonebookController {

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
