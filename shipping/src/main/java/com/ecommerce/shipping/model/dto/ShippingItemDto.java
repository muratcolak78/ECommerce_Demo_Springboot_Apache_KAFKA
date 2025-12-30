package com.ecommerce.shipping.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShippingItemDto {

    private Long productId;

    private String productName;

    private Integer quantity;

}
