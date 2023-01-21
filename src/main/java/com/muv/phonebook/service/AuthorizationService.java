package com.muv.phonebook.service;

import com.muv.phonebook.model.User;
import com.muv.phonebook.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isUserCorrect(String login, String password) {
        login = login.substring(0, login.length() - 1);
        User user = userRepository.findUserByLogin(login);
        return user.getLogin().equals(login) && user.getPassword().equals(password);
    }

}
