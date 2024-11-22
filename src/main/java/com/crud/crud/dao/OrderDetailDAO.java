package com.crud.crud.dao;

import com.crud.crud.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrderDetailDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Crear un nuevo detalle de orden
    public int createOrderDetail(OrderDetail orderDetail) {
        String sql = "INSERT INTO [Order Details] (OrderID, ProductID, UnitPrice, Quantity, Discount) " +
                "VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                orderDetail.getOrderId(),
                orderDetail.getProductId(),
                orderDetail.getUnitPrice(),
                orderDetail.getQuantity(),
                orderDetail.getDiscount()
        );
    }

    // Obtener todos los detalles de órdenes
    public List<OrderDetail> getAllOrderDetails() {
        String sql = "SELECT * FROM [Order Details]";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderDetail.class));
    }

    // Obtener un detalle de orden por OrderID y ProductID
    public OrderDetail getOrderDetailById(Integer orderId, Integer productId) {
        String sql = "SELECT * FROM [Order Details] WHERE OrderID = ? AND ProductID = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(OrderDetail.class), orderId, productId);
    }

    // Actualizar un detalle de orden existente
    public int updateOrderDetail(OrderDetail orderDetail) {
        String sql = "UPDATE [Order Details] SET UnitPrice = ?, Quantity = ?, Discount = ? " +
                "WHERE OrderID = ? AND ProductID = ?";
        return jdbcTemplate.update(sql,
                orderDetail.getUnitPrice(),
                orderDetail.getQuantity(),
                orderDetail.getDiscount(),
                orderDetail.getOrderId(),
                orderDetail.getProductId()
        );
    }

    // Eliminar un detalle de orden
    public int deleteOrderDetail(Integer orderId, Integer productId) {
        String sql = "DELETE FROM [Order Details] WHERE OrderID = ? AND ProductID = ?";
        return jdbcTemplate.update(sql, orderId, productId);
    }
}
