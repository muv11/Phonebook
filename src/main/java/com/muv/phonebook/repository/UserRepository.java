package com.muv.phonebook.repository;

import com.muv.phonebook.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    //CRUD
    //create
    boolean createUser(User user);
    //read
    User getUserById(Long id);
    User getUserByLogin(String login);
    //update
    boolean updateUser(User user);
    //delete
    boolean deleteUser(User user);
    //getAll
    List<User> getAll();
}
