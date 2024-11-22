package com.crud.crud.entity;

import lombok.Data;

@Data
public class OrderDetail {
    private Integer orderId;
    private Integer productId;
    private Double unitPrice;
    private Integer quantity;
    private Double discount;

    // Referencia a Order (Opcional)
    private Order order;
}