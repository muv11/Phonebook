package com.muv.phonebook.service;

import com.muv.phonebook.model.User;
import com.muv.phonebook.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;
    private final LoggedUserManagementService userManagementService;

    public AuthorizationService(UserRepository userRepository, LoggedUserManagementService userManagementService) {
        this.userRepository = userRepository;
        this.userManagementService = userManagementService;
    }

    public boolean isUserCorrect(String login, String password) {
        login = login.substring(0, login.length() - 1);
        User user = userRepository.findUserByLogin(login);
        if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
            userManagementService.setLogin(user.getLogin());
        } else {
            userManagementService.setLogin(null);
        }
        return user.getLogin().equals(login) && user.getPassword().equals(password);
    }

}
