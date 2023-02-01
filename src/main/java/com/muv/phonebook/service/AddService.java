package com.muv.phonebook.service;

import com.muv.phonebook.model.Contact;
import com.muv.phonebook.repository.ContactRepository;
import com.muv.phonebook.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AddService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final LoggedUserManagementService userManagementService;


    public AddService(ContactRepository contactRepository, UserRepository userRepository, LoggedUserManagementService userManagementService) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.userManagementService = userManagementService;
    }

    public void addIdUserField(Contact contact) {
        contact.setIdUser(userRepository.findUserByLogin(userManagementService.getLogin()).getId());
        contactRepository.createContact(contact);
    }
}
