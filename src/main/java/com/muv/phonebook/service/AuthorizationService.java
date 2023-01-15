package com.muv.phonebook.service;

import com.muv.phonebook.model.User;
import com.muv.phonebook.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserRepositoryImpl userRepository;

    public AuthorizationService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserCorrect(String login, String password) {
        User user = userRepository.getUserByLogin(login);
        if (user.getLogin().equals(login)) {
            System.out.println("login correct");
        } else {
            System.out.println("login incorrect");
        }
        if (user.getPassword().equals(password)) {
            System.out.println("pw correct");
        } else {
            System.out.println("pw incorrect");
        }
        return user.getLogin().equals(login) && user.getPassword().equals(password);
    }

}
