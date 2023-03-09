package com.muv.phonebook.repository;

import com.muv.phonebook.model.Contact;
import com.muv.phonebook.model.ContactMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This is repository class that works with contacts table of phonebook database
 * @author muv11
 * @version 1.2 */
@Repository
public class ContactRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ContactRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates new contact in the contacts table
     * @param contact the contacts to be stored in the database */
    public void createContact(Contact contact) {
        String sql = "INSERT INTO contacts (last_name, name, fathers_name, phone_number, city, street, house_number, flat_number, email, id_user) " +
                "VALUES (:last_name, :name, :fathers_name, :phone_number, :city, :street, :house_number, :flat_number, :email, :id_user)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("last_name", contact.getLastName())
                .addValue("name", contact.getName())
                .addValue("fathers_name", contact.getFathersName())
                .addValue("phone_number", contact.getPhoneNumber())
                .addValue("city", contact.getCity())
                .addValue("street", contact.getStreet())
                .addValue("house_number", contact.getHouseNumber())
                .addValue("flat_number", contact.getFlatNumber())
                .addValue("email", contact.getEmail())
                .addValue("id_user", contact.getIdUser());
        jdbcTemplate.update(sql, parameterSource);
    }

    /**
     * Finds contact by its id
     * @param id the id of the contact to be found
     * @return found contact*/
    public Contact findById(Long id) {
        String sql = "SELECT * FROM contacts WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, parameterSource, new ContactMapper());
    }

    /**
     * Finds contacts by their user id (FK)
     * @param idUser the user's id (FK)
     * @return list of contacts */
    public List<Contact> findAllByUserId(Long idUser) {
        String sql = "SELECT * FROM contacts WHERE id_user = :id_user";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id_user", idUser);
        return jdbcTemplate.query(sql, parameterSource, new ContactMapper());
    }

    /**
     * Updates contact in the contacts table
     * @param contact the contact with updated fields
     * @param id the id of the contact to be updated */
    public void updateContact(Contact contact, Long id) {
        String sql = "UPDATE contacts SET last_name = :last_name, name = :name, fathers_name = :fathers_name, phone_number = :phone_number, " +
                "city = :city, street = :street, house_number = :house_number, flat_number = :flat_number, email = :email WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id)
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

    /**
     * Deletes contact in the contacts table
     * @param id the id of the contact to be deleted */
    public void deleteContact(Long id) {
        String sql = "DELETE FROM contacts WHERE id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, parameterSource);
    }

}
