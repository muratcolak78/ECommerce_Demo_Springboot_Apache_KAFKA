package com.ecommerce.order.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderSavedEvent {
    private Long orderId;
    private Long userId;
    private Status status; // veya Status
    private BigDecimal totalAmount;
}
