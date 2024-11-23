package com.example.swing.dao;

import com.example.swing.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(OrderItem orderItem) {
        String sql = "INSERT INTO OrderItem (orderItemId, orderId, itemId, quantity, price) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, orderItem.getOrderItemId(), orderItem.getOrderId(), orderItem.getItemId(), orderItem.getQuantity(), orderItem.getPrice());
    }
}
