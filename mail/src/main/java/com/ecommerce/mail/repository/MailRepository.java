package com.ecommerce.mail.repository;


import com.ecommerce.mail.model.MailObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<MailObject, Long> {
    boolean existsByOrderId(Long orderId);

}
