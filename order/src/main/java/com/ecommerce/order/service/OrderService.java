package com.ecommerce.order.service;


import com.ecommerce.order.model.OrderItem;
import com.ecommerce.order.model.OrderResponseDto;

import java.util.List;

public interface OrderService {
    Long checkOut(Long userId, String header);

    List<OrderItem> findByUserId(Long orderId, Long userId);

    List<OrderResponseDto> getOrders(Long userId);
}
