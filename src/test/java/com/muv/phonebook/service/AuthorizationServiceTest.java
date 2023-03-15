package com.muv.phonebook.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorizationServiceTest {

    @Autowired
    private AuthorizationService authorizationService;

    @Test
    void isUserCorrect() {
        Assertions.assertTrue(authorizationService.isUserCorrect("newsitelogin ", "Pass12345"));
        Assertions.assertFalse(authorizationService.isUserCorrect("newsitelogin ", "passworD5464"));
    }


}