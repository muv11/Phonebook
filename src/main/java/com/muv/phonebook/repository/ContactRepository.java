package com.muv.phonebook.repository;

import com.muv.phonebook.model.Contact;
import com.muv.phonebook.model.ContactMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ContactRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createContact(Contact contact) {
        String sql = "INSERT INTO contacts (last_name, name, fathers_name, phone_number, city, street, house_number, flat_number, email) " +
                "VALUES (:last_name, :name, :fathers_name, :phone_number, :city, :street, :house_number, :flat_number, :email)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("last_name", contact.getLastName())
                .addValue("name", contact.getName())
                .addValue("fathers_name", contact.getFathersName())
                .addValue("phone_number", contact.getPhoneNumber())
                .addValue("city", contact.getCity())
                .addValue("street", contact.getStreet())
                .addValue("house_number", contact.getHouseNumber())
                .addValue("flat_number", contact.getFlatNumber())
                .addValue("email", contact.getEmail());
        jdbcTemplate.queryForObject(sql, parameterSource, new ContactMapper());
    }

    public Contact findById(Long id) {
        String sql = "SELECT * FROM contacts WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, parameterSource, new ContactMapper());
    }

    public List<Contact> findAllByUserId(Long idUser) {
        String sql = "SELECT * FROM contacts WHERE id_user = :idUser";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_user", idUser);
        return jdbcTemplate.query(sql, parameterSource, new ContactMapper());
    }

    public void updateContact(Contact contact, Long id) {
        String sql = "UPDATE contacts SET last_name = :last_name, name = :name, fathers_name = :fathers_name, phone_number = :phone_number, " +
                "city = :city, street = :street, house_number = :house_number, flat_number = :flat_number, email = :email WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("last_name", contact.getLastName())
                .addValue("name", contact.getName())
                .addValue("fathers_name", contact.getFathersName())
                .addValue("phone_number", contact.getPhoneNumber())
                .addValue("city", contact.getCity())
                .addValue("street", contact.getStreet())
                .addValue("house_number", contact.getHouseNumber())
                .addValue("flat_number", contact.getFlatNumber())
                .addValue("email", contact.getEmail());
        jdbcTemplate.update(sql, parameterSource);
    }

    public void deleteContact(Long id) {
        String sql = "DELETE FROM contacts WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, parameterSource);
    }

}
