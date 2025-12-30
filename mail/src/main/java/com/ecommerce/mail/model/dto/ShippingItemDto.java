package com.ecommerce.mail.model.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShippingItemDto {

    private Long productId;

    private String productName;

    private Integer quantity;

}
