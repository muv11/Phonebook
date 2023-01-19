package com.muv.phonebook.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RegistrationServiceTest {

    @Autowired
    private RegistrationService registrationService;

    @Test
    void isPasswordCorrect() {
        Assertions.assertTrue(registrationService.isPasswordCorrect("True675Password"));
        Assertions.assertFalse(registrationService.isPasswordCorrect("FalsePassword"));
        Assertions.assertFalse(registrationService.isPasswordCorrect("false"));
        Assertions.assertFalse(registrationService.isPasswordCorrect("false585858"));
    }

    @Test
    void isEmailCorrect() {
        Assertions.assertTrue(registrationService.isEmailCorrect("email@gmail.com"));
        Assertions.assertFalse(registrationService.isEmailCorrect("emailgmail.com"));
    }

}