package com.ecommerce.order.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column
    private BigDecimal totalAmount;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        this.createdAt=LocalDateTime.now();
        this.updatedAt=LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        this.updatedAt=LocalDateTime.now();
    }
}
