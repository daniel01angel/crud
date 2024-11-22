package com.crud.crud.entity;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Integer orderId; // Puede ser null al crear una nueva orden
    private String customerId;
    private Integer employeeId;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private Integer shipVia;
    private Double freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;

    // Si deseas incluir detalles de la orden
    private List<OrderDetail> orderDetails;
}
