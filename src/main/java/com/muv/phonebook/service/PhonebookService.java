package com.muv.phonebook.service;

import com.muv.phonebook.model.Contact;
import com.muv.phonebook.repository.ContactRepository;
import com.muv.phonebook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This is service class that gets contacts
 * @author muv11
 * @version 1.0 */
@Service
public class PhonebookService {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public PhonebookService(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    private Long getUserIdByLogin(String login) {
        return userRepository.findUserByLogin(login).getId();
    }

    public List<Contact> getContactList(String login) {
        return contactRepository.findAllByUserId(getUserIdByLogin(login));
    }

}
