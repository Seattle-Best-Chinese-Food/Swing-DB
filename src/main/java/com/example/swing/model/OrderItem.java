package com.example.swing.model;

import java.util.UUID;

public class OrderItem extends Dish {
    private String orderItemId;
    private String orderId;
    private int quantity;

    public OrderItem() {
        this.orderItemId = UUID.randomUUID().toString();
    }

    public OrderItem(String orderId, String itemId, String name, double price, String description, String imageUrl, int quantity) {
        super(name, price, description, imageUrl);
        this.orderItemId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.setItemId(itemId); // Assuming setItemId is accessible
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
