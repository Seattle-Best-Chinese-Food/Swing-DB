package com.example.swing.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import com.example.swing.model.Dish;
import com.example.swing.utils.ButtonUtils;
import java.awt.event.ActionListener;

public class CustomerMenuView {
    private JFrame frame;
    private JPanel cardPanel;
    private JButton viewOrderButton;
    private Map<Dish, JButton> orderButtons = new HashMap<>(); // Store buttons associated with each dish

    public CustomerMenuView() {
        initialize();
    }

    public void initialize() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    public JFrame getFrame() {
        return frame;
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
        cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
        cardPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        viewOrderButton = new JButton("View Order");
        bottomPanel.add(viewOrderButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        frame.add(panel);
    }

    public void addDishCard(JPanel card) {
        cardPanel.add(card);
        cardPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public JButton getViewOrderButton() {
        return viewOrderButton;
    }

    public JButton getOrderButton(Dish dish) {
        return orderButtons.get(dish);
    }

    public JPanel createDishCard(Dish dish, ActionListener orderAction) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        leftPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(dish.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setPreferredSize(new Dimension(100, 150));
        nameLabel.setMaximumSize(new Dimension(100, 150));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(150, 150));
        imageLabel.setMaximumSize(new Dimension(150, 150));
        imageLabel.setBackground(new Color(242, 224, 208));
        imageLabel.setOpaque(true);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setText("Image");
        try {
            ImageIcon icon = new ImageIcon(new URL(dish.getImageUrl()));
            Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.setText("");
        } catch (Exception e) {
            imageLabel.setText("Image not available");
        }

        leftPanel.add(nameLabel);
        leftPanel.add(imageLabel);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 0, 5, 0);

        JLabel priceLabel = new JLabel(String.format("$%.2f", dish.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setVerticalAlignment(SwingConstants.CENTER);

        gbc.gridy = 0;
        centerPanel.add(priceLabel, gbc);

        JLabel descriptionLabel = new JLabel(
                "<html><p style=\"width:250px; text-align:center;\">" + dish.getDescription() + "</p></html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionLabel.setForeground(Color.DARK_GRAY);
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        descriptionLabel.setVerticalAlignment(SwingConstants.CENTER);

        gbc.gridy = 1;
        centerPanel.add(descriptionLabel, gbc);

        JButton orderButton = ButtonUtils.createButton("Add", Color.BLUE);
        orderButton.setPreferredSize(new Dimension(100, 40));
        orderButton.addActionListener(orderAction);

        orderButtons.put(dish, orderButton);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setPreferredSize(new Dimension(120, 150));

        GridBagConstraints buttonGbc = new GridBagConstraints();
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonGbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(orderButton, buttonGbc);

        card.add(leftPanel, BorderLayout.WEST);
        card.add(centerPanel, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.EAST);

        return card;
    }
}
