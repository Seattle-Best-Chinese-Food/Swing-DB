package com.example.swing.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.swing.model.Contact;

import java.util.List;

@Repository
public class ContactDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Create the contacts table if it doesn't exist
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS contacts (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), email VARCHAR(50))";
        jdbcTemplate.execute(sql);
    }

    // Save a new contact
    public int save(Contact contact) {
        String sql = "INSERT INTO contacts (name, email) VALUES (?, ?)";
        return jdbcTemplate.update(sql, contact.getName(), contact.getEmail());
    }

    // Update an existing contact
    public int update(Contact contact) {
        String sql = "UPDATE contacts SET name=?, email=? WHERE id=?";
        return jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getId());
    }

    // Delete a contact by ID
    public int delete(int id) {
        String sql = "DELETE FROM contacts WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    // Retrieve a contact by ID
    public Contact getById(int id) {
        String sql = "SELECT * FROM contacts WHERE id=?";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email")), id);
    }

    // Retrieve all contacts
    public List<Contact> getAll() {
        String sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
    }
}
