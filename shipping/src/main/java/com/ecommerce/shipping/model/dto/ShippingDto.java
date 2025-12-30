package com.ecommerce.shipping.model.dto;

import com.ecommerce.shipping.model.enums.ShippingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDto {

    private Long shippingId;
    private Long orderId;

    private Long userId;

    private String fullName;

    private String street;

    private String zip;

    private String city;

    private String country;

    private List<ShippingItemDto> itemDtoList;


}
