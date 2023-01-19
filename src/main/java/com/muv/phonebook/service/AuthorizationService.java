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
        return user.getLogin().equals(login) && user.getPassword().equals(password);
    }

}
