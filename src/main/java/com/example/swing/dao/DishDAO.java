package com.example.swing.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.swing.model.Dish;

@Repository
public class DishDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Dish dish) {
        String sql = "INSERT INTO Item (itemId, name, price, description, image) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, dish.getItemId(), dish.getName(), dish.getPrice(), dish.getDescription(), dish.getImageUrl());
    }

    public List<Dish> getAll() {
        String sql = "SELECT * FROM Item";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Dish dish = new Dish();
            dish.setItemId(rs.getString("itemId"));
            dish.setName(rs.getString("name"));
            dish.setPrice(rs.getDouble("price"));
            dish.setDescription(rs.getString("description"));
            dish.setImageUrl(rs.getString("image"));
            return dish;
        });
    }

    public void delete(String itemId) {
        String sql = "DELETE FROM Item WHERE itemId = ?";
        jdbcTemplate.update(sql, itemId);
    }

    public void update(String itemId, Dish dish) {
        String sql = "UPDATE Item SET name = ?, price = ?, description = ?, image = ? WHERE itemId = ?";
        jdbcTemplate.update(sql, dish.getName(), dish.getPrice(), dish.getDescription(), dish.getImageUrl(), itemId);
    }
}
