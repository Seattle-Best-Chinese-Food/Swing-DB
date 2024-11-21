package com.example.swing.controller;

import com.example.swing.dao.DishDAO;
import com.example.swing.model.Dish;
import java.util.List;

public class AdminMenuController {
    private DishDAO dishDAO;

    public AdminMenuController(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public List<Dish> getAllDishes() {
        return dishDAO.getAll();
    }

    public void addDish(String name, double price, String description, String imageUrl) {
        Dish dish = new Dish(name, price, description, imageUrl);
        dishDAO.save(dish);
    }

    public void updateDish(String itemId, String name, double price, String description, String imageUrl) {
        Dish dish = new Dish(name, price, description, imageUrl);
        dish.setItemId(itemId);
        dishDAO.update(itemId, dish);
    }

    public void deleteDish(String itemId) {
        dishDAO.delete(itemId);
    }
}