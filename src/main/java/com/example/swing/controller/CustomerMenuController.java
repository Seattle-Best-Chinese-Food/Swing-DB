package com.example.swing.controller;

import com.example.swing.dao.DishDAO;
import com.example.swing.model.Dish;
import com.example.swing.view.CustomerMenuView;
import com.example.swing.view.OrderPopup;
import com.example.swing.view.OrderStatusPage;
import com.example.swing.dao.OrderDAO;
import com.example.swing.dao.OrderItemDAO;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomerMenuController {
    private DishDAO dishDAO;
    private List<Dish> dishes;
    private List<Dish> cart;
    private CustomerMenuView view;
    private OrderPopup orderPopup;

    public CustomerMenuController(DishDAO dishDAO, CustomerMenuView view, OrderDAO orderDAO, OrderItemDAO orderItemDAO) {
        this.dishDAO = dishDAO;
        this.cart = new ArrayList<>();
        this.view = view;
        this.orderPopup = new OrderPopup(view.getFrame(), orderDAO, orderItemDAO);
        loadDishes();
        setupViewOrderButton();
        setupOrderStatusButton(); // 添加 Order Status 按钮处理
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

    private void setupViewOrderButton() {
        view.getViewOrderButton().addActionListener(e -> orderPopup.showOrderPopup(cart));
    }

    private void setupOrderStatusButton() {
        // 为 Order Status 按钮添加点击事件
        view.getOrderStatusButton().addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                // 从购物车中获取订单的菜品名称
                List<String> orderNames = new ArrayList<>();
                for (Dish dish : cart) {
                    orderNames.add(dish.getName()); // 假设 Dish 类有 getName() 方法
                }
    
                // 将订单名称列表传递给 OrderStatusPage
                OrderStatusPage orderStatusPage = new OrderStatusPage(orderNames);
                orderStatusPage.show(); // 显示 OrderStatusPage 界面
            });
        });
    }
}


// package com.example.swing.controller;

// import com.example.swing.dao.DishDAO;
// import com.example.swing.model.Dish;
// import com.example.swing.view.CustomerMenuView;
// import com.example.swing.view.OrderPopup;
// import com.example.swing.dao.OrderDAO;
// import com.example.swing.dao.OrderItemDAO;

// import javax.swing.*;
// import java.awt.event.ActionListener;
// import java.util.ArrayList;
// import java.util.List;


// public class CustomerMenuController {
//     private DishDAO dishDAO;
//     private List<Dish> dishes;
//     private List<Dish> cart;
//     private CustomerMenuView view;
//     private OrderPopup orderPopup;

//     public CustomerMenuController(DishDAO dishDAO, CustomerMenuView view, OrderDAO orderDAO, OrderItemDAO orderItemDAO) {
//         this.dishDAO = dishDAO;
//         this.cart = new ArrayList<>();
//         this.view = view;
//         this.orderPopup = new OrderPopup(view.getFrame(), orderDAO, orderItemDAO);
//         loadDishes();
//         setupViewOrderButton();
//     }

//     private void loadDishes() {
//         dishes = dishDAO.getAll();
//         for (Dish dish : dishes) {
//             ActionListener orderAction = e -> addDishToCart(dish);
//             JPanel card = view.createDishCard(dish, orderAction);
//             view.addDishCard(card);
//         }
//     }

//     private void addDishToCart(Dish dish) {
//         cart.add(dish);
//         view.getViewOrderButton().setText("View Order (" + cart.size() + ")");
//         view.getOrderButton(dish).setText("Success");
//         view.getOrderButton(dish).setEnabled(false);
//     }

//     private void setupViewOrderButton() {
//         view.getViewOrderButton().addActionListener(e -> orderPopup.showOrderPopup(cart));
//     }
// }
