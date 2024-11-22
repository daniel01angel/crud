package com.crud.crud.controller;

import com.crud.crud.entity.OrderDetail;
import com.crud.crud.exception.ResourceNotFoundException;
import com.crud.crud.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    // Crear un nuevo detalle de orden
    @PostMapping
    public ResponseEntity<String> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderDetailService.createOrderDetail(orderDetail);
        return ResponseEntity.ok("OrderDetail created successfully.");
    }

    // Obtener todos los detalles de órdenes
    @GetMapping
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    // Obtener un detalle de orden por OrderID y ProductID
    @GetMapping("/{orderId}/{productId}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable(value = "orderId") Integer orderId,
                                                          @PathVariable(value = "productId") Integer productId) throws ResourceNotFoundException {
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(orderId, productId);
        return ResponseEntity.ok().body(orderDetail);
    }

    // Actualizar un detalle de orden existente
    @PutMapping("/{orderId}/{productId}")
    public ResponseEntity<String> updateOrderDetail(@PathVariable(value = "orderId") Integer orderId,
                                                    @PathVariable(value = "productId") Integer productId,
                                                    @RequestBody OrderDetail orderDetails) throws ResourceNotFoundException {
        orderDetailService.updateOrderDetail(orderId, productId, orderDetails);
        return ResponseEntity.ok("OrderDetail updated successfully.");
    }

    // Eliminar un detalle de orden
    @DeleteMapping("/{orderId}/{productId}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable(value = "orderId") Integer orderId,
                                                    @PathVariable(value = "productId") Integer productId) throws ResourceNotFoundException {
        orderDetailService.deleteOrderDetail(orderId, productId);
        return ResponseEntity.ok("OrderDetail deleted successfully.");
    }
}
