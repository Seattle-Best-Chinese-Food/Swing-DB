package com.example.swing.dao;

import com.example.swing.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrderDAO {
    private OrderItemDAO orderItemDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDAO(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    public int save(Order order) {
        String sql = "INSERT INTO `Order` (orderId, orderDate, totalPrice, status, customerId) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, order.getOrderId(), order.getOrderDate(), order.getTotalPrice(), order.getStatus(), order.getCustomerId());
    }

    public List<Order> getOrders() {
        String sql = "SELECT * FROM `Order`";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Order order = new Order();
            String orderId = rs.getString("orderId");
            order.setOrderId(orderId);
            order.setOrderDate(rs.getDate("orderDate"));
            order.setTotalPrice(rs.getDouble("totalPrice"));
            order.setStatus(rs.getString("status"));
            order.setCustomerId(rs.getString("customerId"));
            order.setStatus(rs.getString("status"));
            order.setOrderItems(orderItemDAO.getItemByOrderId(orderId));
            System.out.println(order + " order");
            return order;
        });
    }

    // update order status
    public Boolean updateOrderStatus(String orderId, String status) {
        String sql = "UPDATE `Order` SET status = ? WHERE orderId = ?";
        return jdbcTemplate.update(sql, status, orderId) > 0;
    }

}
