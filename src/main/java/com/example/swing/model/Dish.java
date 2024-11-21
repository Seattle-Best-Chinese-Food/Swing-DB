package com.example.swing.model;

import java.util.UUID;

public class Dish {
    private String itemId;
    private String name;
    private double price;
    private String description;
    private String imageUrl;

    public Dish() {}

    public Dish(String name, double price, String description, String imageUrl) {
        this.itemId = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getDetails() {
        return name + ": " + description + " ($" + price + ")";
    }

    // Getters and Setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
