package com.ecommerce.mail.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Mails")
@Getter
@Setter
public class MailObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_id", nullable = false, unique=true)
    private Long orderId;
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column
    private String email;
    @Enumerated(EnumType.STRING)
    @Column
    private MailStatus status;
    @Column
    private String body;
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
