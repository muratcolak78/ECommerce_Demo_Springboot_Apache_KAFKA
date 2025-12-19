package com.ecommerce.order.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    @Column(name = "product_id", nullable = false)
    private Long productId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;
    @Column(nullable = false)
    private Integer quantity;
    @Column(name = "line_total", nullable = false)
    private BigDecimal lineTotal;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        calculateLineTotal();
    }

    @PreUpdate
    public void preUpdate(){
        calculateLineTotal();
    }

    public void calculateLineTotal() {
        if (unitPrice != null && quantity != null) {
            this.lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }

}
