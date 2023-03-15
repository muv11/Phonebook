package com.muv.phonebook.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

/**
 * This is service class that saves the login of the user during session
 * @author muv11
 * @version 1.0 */
@Service
@SessionScope
public class LoggedUserManagementService {

    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
