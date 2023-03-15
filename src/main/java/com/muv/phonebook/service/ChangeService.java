package com.muv.phonebook.service;

import com.muv.phonebook.model.Contact;
import com.muv.phonebook.repository.ContactRepository;
import com.muv.phonebook.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * This is service class that changes data of the contacts and deletes contacts
 * @author muv11
 * @version 1.0 */
@Service
public class ChangeService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private final LoggedUserManagementService userManagementService;


    public ChangeService(ContactRepository contactRepository, UserRepository userRepository, LoggedUserManagementService userManagementService) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.userManagementService = userManagementService;
    }

    public Contact getCurrentContact(Long contactId) {
        return contactRepository.findById(contactId);
    }

    public void changeContact(Contact contact) {
        contactRepository.updateContact(contact, contact.getId());
    }

    public void deleteContact(Long id) {
        contactRepository.deleteContact(id);
    }

}
