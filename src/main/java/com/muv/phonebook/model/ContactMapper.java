package com.muv.phonebook.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getLong("id"));
        contact.setLastName(rs.getString("last_name"));
        contact.setName(rs.getString("name"));
        contact.setFathersName(rs.getString("fathers_name"));
        contact.setPhoneNumber(rs.getString("phone_number"));
        contact.setCity(rs.getString("city"));
        contact.setStreet(rs.getString("street"));
        contact.setHouseNumber(rs.getString("house_number"));
        contact.setFlatNumber(rs.getString("flat_number"));
        contact.setEmail(rs.getString("email"));
        contact.setIdUser(rs.getLong("id_user"));
        return contact;
    }
}
