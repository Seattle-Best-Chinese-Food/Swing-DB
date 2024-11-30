package com.example.swing.model;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private Date orderDate;
    private double totalPrice;
    private List<OrderItem> orderItems;
    private String status;
    private String customerId;

    public Order() {}

    public Order(String orderId, Date orderDate, List<OrderItem> orderItems, String st, String customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        // calculate total price by using quantity * price
        this.totalPrice = orderItems.stream()
                .mapToDouble(item -> item.getQuantity() * item.getPrice())
                .sum();
        this.orderItems = orderItems;
        this.status = st;
        this.customerId = customerId;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", orderItems=" + orderItems +
                ", status='" + status + '\'' +
                ", customerId='" + customerId + '\'' +
                '}';
    }
}