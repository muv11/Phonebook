package com.muv.phonebook.repository;

import com.muv.phonebook.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    private final User user1 = new User("user1", "StrPass11", "user1@mail.ru");
    private final User user2 = new User("user2", "StrongPass010", "user2@gmail.com");

    @Order(1)
    @BeforeAll
    void createUser() {
        userRepository.createUser(user1);
        userRepository.createUser(user2);
        user1.setId(userRepository.findUserByLogin("user1").getId());
        user2.setId(userRepository.findUserByLogin("user2").getId());
        Assertions.assertEquals(user1.getPassword(), userRepository.findUserById(user1.getId()).getPassword());
    }

    @Order(2)
    @Test
    void findUserById() {
        Assertions.assertEquals(user2.getEmail(), userRepository.findUserById(user2.getId()).getEmail());
    }

    @Order(3)
    @Test
    void findUserByLogin() {
        Assertions.assertEquals(user1.getId(), userRepository.findUserByLogin("user1").getId());
    }

    @Order(4)
    @Test
    void findAllUsers() {
        Assertions.assertEquals(4, userRepository.findAllUsers().size());
    }

    @Order(5)
    @Test
    void updateUser() {
        User user3 = new User("user2", "StrongPass010", "USER2@gmail.com");
        userRepository.updateUser(user3, user2.getId());
        Assertions.assertEquals(user3.getEmail(), userRepository.findUserById(user2.getId()).getEmail());
    }

    @Order(6)
    @Test
    void deleteUser() {
        userRepository.deleteUser(user2.getId());
        Assertions.assertNull(userRepository.findUserById(user2.getId()));
    }
}