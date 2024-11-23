package com.example.swing.model;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private Date orderDate;
    private double totalPrice;
    private List<OrderItem> orderItems;

    public Order() {}

    public Order(String orderId, Date orderDate, double totalPrice, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
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
}