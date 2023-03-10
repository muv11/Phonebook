package com.muv.phonebook.controller;

import com.muv.phonebook.model.Contact;
import com.muv.phonebook.service.ChangeService;
import com.muv.phonebook.service.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * This is controller class that works with contact data modification requests
 * @author muv11
 * @version 1.0 */
@Controller
public class ChangeController {

    private final ChangeService changeService;
    private final LoggedUserManagementService userManagementService;
    private final PhonebookController phonebookController;
    private Contact contact;

    public ChangeController(ChangeService changeService, LoggedUserManagementService userManagementService, PhonebookController phonebookController) {
        this.changeService = changeService;
        this.userManagementService = userManagementService;
        this.phonebookController = phonebookController;
    }

    @GetMapping("/change/{id}")
    public String getChange(
            @PathVariable Long id,
            Model model
    ) {
        if (userManagementService.getLogin() == null) {
            return "authorization";
        }
        contact = changeService.getCurrentContact(id);
        model.addAttribute("id", contact.getId());
        model.addAttribute("lastName", contact.getLastName());
        model.addAttribute("name", contact.getName());
        model.addAttribute("fathersName", contact.getFathersName());
        model.addAttribute("phoneNumber", contact.getPhoneNumber());
        model.addAttribute("city", contact.getCity());
        model.addAttribute("street", contact.getStreet());
        model.addAttribute("houseNumber", contact.getHouseNumber());
        model.addAttribute("flatNumber", contact.getFlatNumber());
        model.addAttribute("email", contact.getEmail());
        return "change";
    }

    @PostMapping( "/change")
    public String postChange(
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
        contact.setLastName(lastName);
        contact.setName(name);
        contact.setFathersName(fathersName);
        contact.setPhoneNumber(phoneNumber);
        contact.setCity(city);
        contact.setStreet(street);
        contact.setHouseNumber(houseNumber);
        contact.setFlatNumber(flatNumber);
        contact.setEmail(email);

        changeService.changeContact(contact);
        return phonebookController.getPhonebook(model);
    }

    @GetMapping("/change-delete/{id}")
    public String getDelete(
            @PathVariable Long id,
            Model model
    )
    {
        return postDelete(id, model);
    }

    @PostMapping("/change-delete")
    public String postDelete(
            Long id,
            Model model
    ) {
        changeService.deleteContact(id);
        return phonebookController.getPhonebook(model);
    }

}
