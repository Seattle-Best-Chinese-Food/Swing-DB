package com.example.swing.view;

import com.example.swing.model.Dish;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderPopup {
    private JDialog orderDialog;
    private JPanel orderPanel;
    private JLabel totalLabel;
    private Map<Dish, Integer> quantities;

    public OrderPopup(JFrame parentFrame) {
        orderDialog = new JDialog(parentFrame, "Your Order", true);
        orderDialog.setSize(400, 300);
        orderDialog.setLayout(new BorderLayout());

        orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

        totalLabel = new JLabel();
        JButton submitButton = new JButton("Submit Order");

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(totalLabel);
        bottomPanel.add(submitButton);

        orderDialog.add(new JScrollPane(orderPanel), BorderLayout.CENTER);
        orderDialog.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void showOrderPopup(List<Dish> cart) {
        orderPanel.removeAll();
        quantities = new HashMap<>();
        double totalPrice = 0.0;

        for (Dish dish : cart) {
            quantities.put(dish, 1); // Default quantity

            JPanel dishPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            JLabel nameLabel = new JLabel(dish.getName());
            nameLabel.setPreferredSize(new Dimension(80, 30)); // Set fixed width
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            dishPanel.add(nameLabel, gbc);

            JPanel counterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JTextField quantityField = new JTextField("1", 3);
            quantityField.setHorizontalAlignment(JTextField.CENTER);
            JButton minusButton = new JButton("-");
            JButton plusButton = new JButton("+");

            minusButton.addActionListener(e -> {
                int quantity = quantities.get(dish);
                if (quantity > 1) {
                    quantities.put(dish, quantity - 1);
                    quantityField.setText(String.valueOf(quantity - 1));
                    updateTotalPrice();
                }
            });

            plusButton.addActionListener(e -> {
                int quantity = quantities.get(dish);
                quantities.put(dish, quantity + 1);
                quantityField.setText(String.valueOf(quantity + 1));
                updateTotalPrice();
            });

            counterPanel.add(minusButton);
            counterPanel.add(quantityField);
            counterPanel.add(plusButton);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.CENTER;
            dishPanel.add(counterPanel, gbc);

            JLabel priceLabel = new JLabel(String.format("$%.2f", dish.getPrice()));
            gbc.gridx = 2;
            gbc.anchor = GridBagConstraints.EAST;
            dishPanel.add(priceLabel, gbc);

            orderPanel.add(dishPanel);

            totalPrice += dish.getPrice();
        }

        totalLabel.setText("Total: $" + String.format("%.2f", totalPrice));
        orderDialog.setVisible(true);
    }

    private void updateTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<Dish, Integer> entry : quantities.entrySet()) {
            totalPrice += entry.getKey().getPrice() * entry.getValue();
        }
        totalLabel.setText("Total: $" + String.format("%.2f", totalPrice));
    }
} 