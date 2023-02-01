package com.muv.phonebook.service;

import com.muv.phonebook.model.User;
import com.muv.phonebook.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SettingsService {

    private final RegistrationService registrationService;
    private final UserRepository userRepository;
    private final LoggedUserManagementService userManagementService;


    public SettingsService(RegistrationService registrationService, UserRepository userRepository, LoggedUserManagementService userManagementService) {
        this.registrationService = registrationService;
        this.userRepository = userRepository;
        this.userManagementService = userManagementService;
    }

    public boolean updateUser(User user) {
        if (registrationService.isEmailCorrect(user.getEmail()) && registrationService.isPasswordCorrect(user.getPassword())) {
            userRepository.updateUser(user, userRepository.findUserByLogin(userManagementService.getLogin()).getId());
            return true;
        }
        return false;
    }

    public String getCurrentLogin() {
        return userManagementService.getLogin();
    }

    public String getCurrentPassword() {
        return userRepository.findUserByLogin(userManagementService.getLogin()).getPassword();
    }

    public String getCurrentEmail() {
        return userRepository.findUserByLogin(userManagementService.getLogin()).getEmail();
    }

}
