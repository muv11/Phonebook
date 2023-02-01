package com.muv.phonebook.repository;

import com.muv.phonebook.model.User;
import com.muv.phonebook.model.UserMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is repository class that works with users table of phonebook database
 * @author muv11
 * @version 2.2 */

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates new user in the users table
     * @param user the user to be stored in the database */
    public void createUser(User user) {
        String sql = "INSERT INTO users (login, password, email) VALUES (:login, :password, :email)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail());
        jdbcTemplate.update(sql, parameterSource);
    }

    /**
     * Finds user by its id
     * @param id the id of the user to be found
     * @return found user*/
    public User findUserById(Long id) {
        String sql = "SELECT * FROM users WHERE users.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, parameterSource, new UserMapper());
    }

    /**
     * Finds user by its login
     * @param login the login of the user to be found
     * @return found user */
    public User findUserByLogin(String login) {
        String sql = "SELECT * FROM users WHERE users.login = :login";
        SqlParameterSource parameterSource = new MapSqlParameterSource("login", login);
        return jdbcTemplate.queryForObject(sql, parameterSource, new UserMapper());
    }

    /**
     * Finds all users in the database
     * @return list of users */
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    /**
     * Updates user in the users table
     * @param user the user with updated fields
     * @param id the id of the user to be updated */
    public void updateUser(User user, Long id) {
        String sql = "UPDATE users SET login = :login, password = :password, email = :email WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail());
        jdbcTemplate.update(sql, parameterSource);
    }

    /**
     * Deletes user in the users table
     * @param id the id of the user to be deleted */
    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, parameterSource);
    }

}
