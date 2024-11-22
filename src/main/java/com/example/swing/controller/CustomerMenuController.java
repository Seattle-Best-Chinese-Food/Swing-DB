package com.example.swing.controller;

import com.example.swing.dao.DishDAO;
import com.example.swing.model.Dish;
import com.example.swing.view.CustomerMenuView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomerMenuController {
    private DishDAO dishDAO;
    private List<Dish> dishes;
    private List<Dish> cart;
    private CustomerMenuView view;

    public CustomerMenuController(DishDAO dishDAO, CustomerMenuView view) {
        this.dishDAO = dishDAO;
        this.cart = new ArrayList<>();
        this.view = view;
        loadDishes();
    }

    private void loadDishes() {
        dishes = dishDAO.getAll();
        for (Dish dish : dishes) {
            ActionListener orderAction = e -> addDishToCart(dish);
            JPanel card = view.createDishCard(dish, orderAction);
            view.addDishCard(card);
        }
    }

    private void addDishToCart(Dish dish) {
        cart.add(dish);
        view.getViewOrderButton().setText("View Order (" + cart.size() + ")");
        view.getOrderButton(dish).setText("Success");
        view.getOrderButton(dish).setEnabled(false);
    }
}
