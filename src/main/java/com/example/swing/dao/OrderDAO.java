package com.example.swing.dao;

import com.example.swing.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Order order) {
        String sql = "INSERT INTO `Order` (orderId, orderDate, totalPrice) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, order.getOrderId(), order.getOrderDate(), order.getTotalPrice());
    }
    
}
