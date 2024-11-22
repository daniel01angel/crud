package com.crud.crud.service;

import com.crud.crud.dao.OrderDetailDAO;
import com.crud.crud.entity.OrderDetail;
import com.crud.crud.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    // Crear un nuevo detalle de orden
    public void createOrderDetail(OrderDetail orderDetail) {
        orderDetailDAO.createOrderDetail(orderDetail);
    }

    // Obtener todos los detalles de órdenes
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailDAO.getAllOrderDetails();
    }

    // Obtener un detalle de orden por OrderID y ProductID
    public OrderDetail getOrderDetailById(Integer orderId, Integer productId) throws ResourceNotFoundException {
        try {
            return orderDetailDAO.getOrderDetailById(orderId, productId);
        } catch (Exception e) {
            throw new ResourceNotFoundException("OrderDetail not found for OrderID :: " + orderId + " and ProductID :: " + productId);
        }
    }

    // Actualizar un detalle de orden existente
    public void updateOrderDetail(Integer orderId, Integer productId, OrderDetail orderDetails) throws ResourceNotFoundException {
        OrderDetail existingDetail = getOrderDetailById(orderId, productId);
        existingDetail.setUnitPrice(orderDetails.getUnitPrice());
        existingDetail.setQuantity(orderDetails.getQuantity());
        existingDetail.setDiscount(orderDetails.getDiscount());

        orderDetailDAO.updateOrderDetail(existingDetail);
    }

    // Eliminar un detalle de orden
    public void deleteOrderDetail(Integer orderId, Integer productId) throws ResourceNotFoundException {
        OrderDetail existingDetail = getOrderDetailById(orderId, productId);
        orderDetailDAO.deleteOrderDetail(existingDetail.getOrderId(), existingDetail.getProductId());
    }
}
