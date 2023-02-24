package com.muv.phonebook.controller;

import com.muv.phonebook.model.Contact;
import com.muv.phonebook.service.AddService;
import com.muv.phonebook.service.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This is controller class that works with adding new contacts requests
 * @author muv11
 * @version 2.1 */
@Controller
public class AddController {

    private final AddService addService;
    private final LoggedUserManagementService userManagementService;
    private final PhonebookController phonebookController;

    public AddController(AddService addService, LoggedUserManagementService userManagementService, PhonebookController phonebookController) {
        this.addService = addService;
        this.userManagementService = userManagementService;
        this.phonebookController = phonebookController;
    }

    @GetMapping("/add")
    public String getAdd() {
        if (userManagementService.getLogin() == null) {
            return "authorization";
        }
        return "add";
    }

    @PostMapping("/add")
    public String postAdd(
            @RequestParam String lastName,
            @RequestParam String name,
            @RequestParam String fathersName,
            @RequestParam String phoneNumber,
            @RequestParam String city,
            @RequestParam String street,
            @RequestParam String houseNumber,
            @RequestParam String flatNumber,
            @RequestParam String email,
            Model model
    ) {
        addService.addIdUserField(new Contact(lastName, name, fathersName, phoneNumber, city, street, houseNumber, flatNumber, email));
        return phonebookController.getPhonebook(model);
    }

}
