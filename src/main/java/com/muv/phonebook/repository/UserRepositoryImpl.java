package com.muv.phonebook.repository;

import com.muv.phonebook.model.User;
import com.muv.phonebook.model.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_USER = "SELECT * FROM users WHERE id = ?";
    private final String SQL_FIND_ALL = "SELECT * FROM users";
    private final String SQL_DELETE_USER = "DELETE * FROM users WHERE id = ?";
    private final String SQL_UPDATE_USER = "UPDATE users SET login = ? password = ? email = ? WHERE id = ?";
    private final String SQL_INSERT_USER = "INSERT INTO users(login, password, email) VALUES(?, ?, ?)";

    public UserRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean createUser(User user) {
        return jdbcTemplate.update(SQL_INSERT_USER, user.getLogin(), user.getPassword(), user.getEmail()) > 0;
    }

    @Override
    public User getUserById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_USER, new UserMapper(), id);
    }

    @Override
    public boolean updateUser(User user) {
        return jdbcTemplate.update(SQL_UPDATE_USER, user.getLogin(), user.getPassword(), user.getEmail()) > 0;
    }

    @Override
    public boolean deleteUser(User user) {
        return jdbcTemplate.update(SQL_DELETE_USER, user.getId()) > 0;
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, new UserMapper());
    }
}
