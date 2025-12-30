package com.ecommerce.shipping.controller;

import com.ecommerce.shipping.model.dto.ShippingDto;
import com.ecommerce.shipping.service.ShippingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ecommerce/shipping")
public class ShippingController {
    private final ShippingService service;

    public ShippingController(ShippingService service) {
        this.service = service;
    }
    @GetMapping("/ships")
    public ResponseEntity<List<ShippingDto>> getShips(){
        List<ShippingDto> dtoList= service.getShips();
        return ResponseEntity.ok(dtoList);
    }
}
