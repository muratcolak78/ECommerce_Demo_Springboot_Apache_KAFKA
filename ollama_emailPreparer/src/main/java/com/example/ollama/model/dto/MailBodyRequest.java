package com.example.ollama.model.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MailBodyRequest {
    private Long shippingId;
    private Long orderId;
    private String fullName;
    private List<ShippingItemDto> itemDtoList;
}
