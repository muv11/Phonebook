package com.muv.phonebook.repository;

import com.muv.phonebook.model.Contact;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private UserRepository userRepository;
    private final Contact contact1 = new Contact("lastName1", "name1", "fathersName1", "777", "Moscow", "Street", "20", "67", "contact1@mail.ru");
    private final Contact contact2 = new Contact("lastName2", "name2", "fathersName2", "88006787312", "NY", "Street", "88", "900", "contact2@gmail.com");

    @BeforeAll
    void createContact() {
        contact1.setIdUser(43L);
        contact2.setIdUser(43L);
        contactRepository.createContact(contact1);
        contactRepository.createContact(contact2);
        contact1.setId(contactRepository.findByPhoneNumber(contact1.getPhoneNumber()).getId());
        contact2.setId(contactRepository.findByPhoneNumber(contact2.getPhoneNumber()).getId());
        Assertions.assertEquals(contact1.getLastName(), contactRepository.findById(contact1.getId()).getLastName());
    }

    @Order(1)
    @Test
    void findById() {
        Assertions.assertEquals(contact1.getEmail(), contactRepository.findById(contact1.getId()).getEmail());
    }

    @Order(2)
    @Test
    void findAllByUserId() {
        Assertions.assertEquals(2, contactRepository.findAllByUserId(contact1.getIdUser()).size());
    }

    @Order(3)
    @Test
    void findByPhoneNumber() {
        Assertions.assertEquals(contact2.getName(), contactRepository.findByPhoneNumber(contact2.getPhoneNumber()).getName());
    }

    @Order(4)
    @Test
    void updateContact() {
        Contact contact3 = new Contact("LASTNAME1", "NAME1", "FATHERSNAME1", "777", "Moscow", "Street", "20", "67", "contact1@mail.ru");
        contact3.setIdUser(contact1.getIdUser());
        contactRepository.updateContact(contact3, contact1.getId());
        Assertions.assertEquals(contact3.getLastName(), contactRepository.findById(contact1.getId()).getLastName());
        Assertions.assertEquals(contact3.getName(), contactRepository.findById(contact1.getId()).getName());
        Assertions.assertEquals(contact3.getFathersName(), contactRepository.findById(contact1.getId()).getFathersName());
    }

    @Order(5)
    @Test
    void deleteContact() {
        contactRepository.deleteContact(contact1.getId());
        contactRepository.deleteContact(contact2.getId());
        userRepository.deleteUser(contact1.getIdUser());
        Assertions.assertNull(contactRepository.findById(contact1.getId()));
        Assertions.assertNull(contactRepository.findById(contact2.getId()));
    }
}