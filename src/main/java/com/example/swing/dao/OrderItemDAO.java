package com.example.swing.dao;

import com.example.swing.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.swing.model.Dish;
import java.util.List;

@Repository
public class OrderItemDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    

    // save order item
    public int save(OrderItem orderItem) {
        String sql = "INSERT INTO OrderItem (orderItemId, orderId, itemId, quantity, price, name) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, orderItem.getOrderItemId(), orderItem.getOrderId(), orderItem.getItemId(), orderItem.getQuantity(), orderItem.getPrice(), orderItem.getName());
    }

    // get order items by order id
    public List<OrderItem> getItemByOrderId(String orderId) {
        String sql = "SELECT * FROM OrderItem WHERE orderId = ?";
        return jdbcTemplate.query(sql, new Object[]{orderId}, new int[]{java.sql.Types.VARCHAR}, (rs, rowNum) -> {
            OrderItem dish = new OrderItem();
            dish.setItemId(rs.getString("itemId"));
            dish.setPrice(rs.getDouble("price"));
            dish.setName(rs.getString("name"));
            dish.setQuantity(rs.getInt("quantity"));
            return dish;
        });
    }
}
