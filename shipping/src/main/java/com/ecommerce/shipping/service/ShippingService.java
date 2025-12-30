package com.ecommerce.shipping.service;


import com.ecommerce.events.shipping.ShippingEvent;
import com.ecommerce.shipping.model.dto.ShippingDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShippingService {
    void getShippingEvent(ShippingEvent event);

    List<ShippingDto> getShips();
}
