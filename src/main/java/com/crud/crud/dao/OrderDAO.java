package com.crud.crud.dao;

import com.crud.crud.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrderDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Crear una nueva orden y devolver el OrderID generado
    public Integer createOrder(Order order) {
        String sql = "INSERT INTO Orders (CustomerID, EmployeeID, OrderDate, RequiredDate, ShippedDate, ShipVia, Freight, ShipName, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, order.getCustomerId());
                ps.setInt(2, order.getEmployeeId());
                ps.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
                ps.setDate(4, new java.sql.Date(order.getRequiredDate().getTime()));
                ps.setDate(5, order.getShippedDate() != null ? new java.sql.Date(order.getShippedDate().getTime()) : null);
                ps.setInt(6, order.getShipVia());
                ps.setDouble(7, order.getFreight());
                ps.setString(8, order.getShipName());
                ps.setString(9, order.getShipAddress());
                ps.setString(10, order.getShipCity());
                ps.setString(11, order.getShipRegion());
                ps.setString(12, order.getShipPostalCode());
                ps.setString(13, order.getShipCountry());
                return ps;
            }
        }, keyHolder);

        Number key = keyHolder.getKey();
        return key != null ? key.intValue() : null;
    }

    // Obtener todas las órdenes
    public List<Order> getAllOrders() {
        String sql = "SELECT * FROM Orders";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Order.class));
    }

    // Obtener una orden por ID
    public Order getOrderById(Integer orderId) {
        String sql = "SELECT * FROM Orders WHERE OrderID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Order.class), orderId);
    }

    // Actualizar una orden existente
    public int updateOrder(Order order) {
        String sql = "UPDATE Orders SET CustomerID = ?, EmployeeID = ?, OrderDate = ?, RequiredDate = ?, ShippedDate = ?, " +
                "ShipVia = ?, Freight = ?, ShipName = ?, ShipAddress = ?, ShipCity = ?, ShipRegion = ?, " +
                "ShipPostalCode = ?, ShipCountry = ? WHERE OrderID = ?";
        return jdbcTemplate.update(sql,
                order.getCustomerId(),
                order.getEmployeeId(),
                order.getOrderDate(),
                order.getRequiredDate(),
                order.getShippedDate(),
                order.getShipVia(),
                order.getFreight(),
                order.getShipName(),
                order.getShipAddress(),
                order.getShipCity(),
                order.getShipRegion(),
                order.getShipPostalCode(),
                order.getShipCountry(),
                order.getOrderId()
        );
    }

    // Eliminar una orden
    public int deleteOrder(Integer orderId) {
        String sql = "DELETE FROM Orders WHERE OrderID = ?";
        return jdbcTemplate.update(sql, orderId);
    }
}
