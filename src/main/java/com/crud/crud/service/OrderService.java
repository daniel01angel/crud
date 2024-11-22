package com.crud.crud.service;

import com.crud.crud.dao.OrderDAO;
import com.crud.crud.entity.Order;
import com.crud.crud.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    // Crear una nueva orden y establecer el OrderID generado
    public Order createOrder(Order order) {
        Integer generatedId = orderDAO.createOrder(order);
        order.setOrderId(generatedId);
        return order;
    }

    // Obtener todas las órdenes
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    // Obtener una orden por ID
    public Order getOrderById(Integer orderId) throws ResourceNotFoundException {
        try {
            return orderDAO.getOrderById(orderId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Order not found for this id :: " + orderId);
        }
    }

    // Actualizar una orden existente
    public void updateOrder(Integer orderId, Order orderDetails) throws ResourceNotFoundException {
        Order existingOrder = getOrderById(orderId);
        existingOrder.setCustomerId(orderDetails.getCustomerId());
        existingOrder.setEmployeeId(orderDetails.getEmployeeId());
        existingOrder.setOrderDate(orderDetails.getOrderDate());
        existingOrder.setRequiredDate(orderDetails.getRequiredDate());
        existingOrder.setShippedDate(orderDetails.getShippedDate());
        existingOrder.setShipVia(orderDetails.getShipVia());
        existingOrder.setFreight(orderDetails.getFreight());
        existingOrder.setShipName(orderDetails.getShipName());
        existingOrder.setShipAddress(orderDetails.getShipAddress());
        existingOrder.setShipCity(orderDetails.getShipCity());
        existingOrder.setShipRegion(orderDetails.getShipRegion());
        existingOrder.setShipPostalCode(orderDetails.getShipPostalCode());
        existingOrder.setShipCountry(orderDetails.getShipCountry());

        orderDAO.updateOrder(existingOrder);
    }

    // Eliminar una orden
    public void deleteOrder(Integer orderId) throws ResourceNotFoundException {
        Order existingOrder = getOrderById(orderId);
        orderDAO.deleteOrder(existingOrder.getOrderId());
    }
}
