package com.example.swing.ui;

import javax.swing.*;
import java.awt.*;
import com.example.swing.dao.DishDAO;
import com.example.swing.model.Dish;
import java.util.List;
import com.example.swing.utils.ButtonUtils;
import java.net.URL;

public class CustomerMenuPage {
    private JFrame frame;
    private DishDAO dishDAO;
    private List<Dish> dishes;
    private JPanel cardPanel;

    public CustomerMenuPage(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public void initialize() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        frame = new JFrame("Customer Menu");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();

        frame.setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel menuLabel = new JLabel("Menu", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(menuLabel, BorderLayout.NORTH);

        cardPanel = new JPanel();
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS)); // Stack cards vertically
        cardPanel.setBackground(Color.WHITE);

        loadDishes();

        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Optional: smoother scrolling
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton viewOrderButton = new JButton("View Order");
        bottomPanel.add(viewOrderButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);
    }

    // Load the dishes from the DAO
    private void loadDishes() {
        dishes = dishDAO.getAll();
        for (Dish d : dishes) {
            createDishCard(d.getName(), d.getImageUrl(), d.getPrice(), d.getDescription());
        }
    }

    // Create a dish card with components arranged horizontally, including the name label
    private void createDishCard(String name, String imgUrl, Double price, String details) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180)); // Allow card to expand horizontally

        // Left Panel containing name label and image label
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        leftPanel.setBackground(Color.WHITE);

        // Name Label
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setPreferredSize(new Dimension(100, 150));
        nameLabel.setMaximumSize(new Dimension(100, 150));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Add some space between name and image

        // Image Label
        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(150, 150));
        imageLabel.setMaximumSize(new Dimension(150, 150));
        imageLabel.setBackground(new Color(242, 224, 208));
        imageLabel.setOpaque(true);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setText("Image"); // Placeholder text
        // Optionally, load image from URL
        try {
            ImageIcon icon = new ImageIcon(new URL(imgUrl));
            Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.setText(""); // Remove placeholder text
        } catch (Exception e) {
            imageLabel.setText("Image not available");
        }

        // Add nameLabel and imageLabel to leftPanel
        leftPanel.add(nameLabel);
        leftPanel.add(imageLabel);

        // Center Panel to center priceLabel and descriptionLabel both vertically and horizontally
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0);

        // Price Label
        JLabel priceLabel = new JLabel(String.format("$%.2f", price));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setVerticalAlignment(SwingConstants.CENTER);

        gbc.gridy = 0;
        centerPanel.add(priceLabel, gbc);

        // Description Label
        JLabel descriptionLabel = new JLabel("<html><p style=\"width:250px; text-align:center;\">" + details + "</p></html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionLabel.setForeground(Color.DARK_GRAY);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setVerticalAlignment(SwingConstants.CENTER);

        gbc.gridy = 1;
        centerPanel.add(descriptionLabel, gbc);

        // Order Button
        JButton orderButton = ButtonUtils.createButton("Add", Color.BLUE);
        orderButton.setPreferredSize(new Dimension(100, 40));

        // Create a panel to hold the order button
        JPanel buttonPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout to center the button
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(120, 150));

        // Add the order button to the button panel
        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonGbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(orderButton, buttonGbc);

        // Add components to card
        card.add(leftPanel, BorderLayout.WEST);
        card.add(centerPanel, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.EAST);

        // Add spacing between cards
        cardPanel.add(card);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
}
