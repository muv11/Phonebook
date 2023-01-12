package com.muv.phonebook.service;

import com.muv.phonebook.model.User;
import com.muv.phonebook.repository.UserRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RegistrationService {

    private final UserRepositoryImpl userRepository;

    public RegistrationService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * (?=.*[0-9]) a digit must occur at least once
     * (?=.*[a-z]) a lower case alphabet must occur at least once
     * (?=.*[A-Z]) an upper case alphabet that must occur at least once
     * .{8, 20} at least 8 characters and at most 20 characters */
    public boolean isPasswordCorrect(String password) {
        String regexPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        return Pattern.compile(regexPattern).matcher(password).matches();
    }

    /**
     *(.+)@(\S+) a @ must occur
     * .{5, 35} at least 5 characters and at most 35 characters */
    public boolean isEmailCorrect(String email) {
        String regexPattern = "^(.+)@(\\S+).{5,35}$";
        return Pattern.compile(regexPattern).matcher(email).matches();
    }

    public boolean registerUser(User user) {
        if (isEmailCorrect(user.getEmail()) && isPasswordCorrect(user.getPassword())) {
            userRepository.createUser(user);
            System.out.println("user register");
            return true;
        }
        System.out.println("user dont register");
        return false;
    }

}
