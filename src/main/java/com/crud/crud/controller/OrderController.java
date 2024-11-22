package com.crud.crud.controller;

import com.crud.crud.entity.Order;
import com.crud.crud.exception.ResourceNotFoundException;
import com.crud.crud.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Crear una nueva orden
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    // Obtener todas las órdenes
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Obtener una orden por ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Integer orderId) throws ResourceNotFoundException {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok().body(order);
    }

    // Actualizar una orden existente
    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable(value = "id") Integer orderId, @Valid @RequestBody Order orderDetails) throws ResourceNotFoundException {
        orderService.updateOrder(orderId, orderDetails);
        return ResponseEntity.ok("Order updated successfully.");
    }

    // Eliminar una orden
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(value = "id") Integer orderId) throws ResourceNotFoundException {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok("Order deleted successfully.");
    }
}
