package com.example.swing.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.swing.dao.OrderDAO;
import com.example.swing.dao.OrderItemDAO;

@Configuration
public class AppConfig {
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public OrderItemDAO orderItemDAO() {
        return new OrderItemDAO();
    }

    @Bean
    public OrderDAO orderDAO(OrderItemDAO orderItemDAO) {
        return new OrderDAO(orderItemDAO);
    }
}
