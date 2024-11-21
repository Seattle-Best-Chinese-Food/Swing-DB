package com.example.swing.ui;

import com.example.swing.dao.ContactDAO;
import com.example.swing.model.Contact;
import com.example.swing.dao.DishDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ContactFrame {
    private ContactDAO contactDAO;
    private DishDAO dishDAO;
    private JFrame frame;

    private JTextField nameField;
    private JTextField emailField;
    private JTable table;
    private DefaultTableModel tableModel;

    public ContactFrame(ContactDAO contactDAO, DishDAO dishDAO) {
        this.contactDAO = contactDAO;
        this.dishDAO = dishDAO;
        // Do not initialize GUI components here
    }

    public void initialize() {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        frame = new JFrame("Contact CRUD Application");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents(); // Build GUI components
        contactDAO.createTable();
        loadContacts();

        frame.setVisible(true);
    }
    private void switchToAdminMenu() {
        // Dispose of the current frame to close it
        frame.dispose();
    
        // Initialize and display the AdminMenuPage
        AdminMenuPage adminMenuPage = new AdminMenuPage(dishDAO);
        adminMenuPage.initialize();
    }

    private void initComponents() {
        JButton switchButton = new JButton("Admin Menu");
        switchButton.addActionListener(e -> switchToAdminMenu());

        // Input fields and buttons
        nameField = new JTextField(15);
        emailField = new JTextField(15);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> addContact());

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> updateContact());

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteContact());

        // Table to display contacts
        tableModel = new DefaultTableModel(new Object[] { "ID", "Name", "Email" }, 0);
        table = new JTable(tableModel);

        // Layout setup
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(addButton);
        inputPanel.add(updateButton);
        inputPanel.add(deleteButton);

             // Add the new button to the input panel
        inputPanel.add(switchButton);

        JScrollPane tableScrollPane = new JScrollPane(table);

        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        frame.getContentPane().add(tableScrollPane, BorderLayout.CENTER);
    }

    private void addContact() {
        String name = nameField.getText();
        String email = emailField.getText();
        Contact contact = new Contact(0, name, email);
        contactDAO.save(contact);
        loadContacts();
    }

    private void updateContact() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer) tableModel.getValueAt(selectedRow, 0);
            String name = nameField.getText();
            String email = emailField.getText();
            Contact contact = new Contact(id, name, email);
            contactDAO.update(contact);
            loadContacts();
        } else {
            JOptionPane.showMessageDialog(frame, "Select a contact to update.");
        }
    }

    private void deleteContact() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (Integer) tableModel.getValueAt(selectedRow, 0);
            contactDAO.delete(id);
            loadContacts();
        } else {
            JOptionPane.showMessageDialog(frame, "Select a contact to delete.");
        }
    }

    private void loadContacts() {
        List<Contact> contacts = contactDAO.getAll();
        tableModel.setRowCount(0);
        for (Contact c : contacts) {
            tableModel.addRow(new Object[] { c.getId(), c.getName(), c.getEmail() });
        }
    }
}
