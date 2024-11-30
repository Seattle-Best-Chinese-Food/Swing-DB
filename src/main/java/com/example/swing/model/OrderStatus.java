package com.example.swing.model;

public enum OrderStatus {
    PENDING("Pending"),
    PROCESSING("Processing"),
    FINISHED("Finished");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}