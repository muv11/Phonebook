package com.muv.phonebook.service;

import com.muv.phonebook.model.User;
import com.muv.phonebook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * (?=.*[0-9]) a digit must occur at least once
     * (?=.*[a-z]) a lower case alphabet must occur at least once
     * (?=.*[A-Z]) an upper case alphabet that must occur at least once
     * .{8, 20} at least 8 characters and at most 20 characters */
    protected boolean isPasswordCorrect(String password) {
        String regexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        return Pattern.compile(regexPattern).matcher(password).matches();
    }

    /**
     *(.+)@(\S+) a @ must occur
     * .{5, 35} at least 5 characters and at most 35 characters */
    protected boolean isEmailCorrect(String email) {
        String regexPattern = "^(.+)@(\\S+).{5,35}$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    public boolean registerUser(User user) {
        if (isEmailCorrect(user.getEmail()) && isPasswordCorrect(user.getPassword())) {
            userRepository.createUser(user);
            return true;
        }
        return false;
    }

}
