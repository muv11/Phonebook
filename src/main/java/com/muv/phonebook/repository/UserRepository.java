package com.muv.phonebook.repository;

import com.muv.phonebook.model.User;
import com.muv.phonebook.model.UserMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(User user) {
        String sql = "INSERT INTO users (login, password, email) VALUES (:login, :password, :email)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail());
        jdbcTemplate.queryForObject(sql, parameterSource, new UserMapper());
    }

    public User findUserById(Long id) {
        String sql = "SELECT * FROM users WHERE users.id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, parameterSource, new UserMapper());
    }

    public User findUserByLogin(String login) {
        String sql = "SELECT * FROM users WHERE users.login = :login";
        SqlParameterSource parameterSource = new MapSqlParameterSource("login", login);
        return jdbcTemplate.queryForObject(sql, parameterSource, new UserMapper());
    }

    public List<User> findAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public void updateUser(User user, Long id) {
        String sql = "UPDATE users SET login = :login, password = :password, email = :email WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("email", user.getEmail());
        jdbcTemplate.update(sql, parameterSource);
    }

    public void deleteUser(Long id) {
        String sql = "DELETE FROM users WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, parameterSource);
    }

}
