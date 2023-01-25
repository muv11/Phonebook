package com.muv.phonebook.service;

import com.muv.phonebook.model.Contact;
import com.muv.phonebook.model.User;
import com.muv.phonebook.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhonebookService {

    private final ContactRepository contactRepository;

    public PhonebookService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContactList(User user) {
        return contactRepository.findAllByUserId(user.getId());
    }

}
