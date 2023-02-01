package com.muv.phonebook.controller;

import com.muv.phonebook.model.Contact;
import com.muv.phonebook.service.AddService;
import com.muv.phonebook.service.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This is controller class that works with adding new contacts requests
 * @author muv11
 * @version 2.0 */
@Controller
public class AddController {

    private final AddService addService;
    private final LoggedUserManagementService userManagementService;

    public AddController(AddService addService, LoggedUserManagementService userManagementService) {
        this.addService = addService;
        this.userManagementService = userManagementService;
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
            @RequestParam String email
    ) {
        addService.addIdUserField(new Contact(lastName, name, fathersName, phoneNumber, city, street, houseNumber, flatNumber, email));
        return "phonebook";
    }

}
