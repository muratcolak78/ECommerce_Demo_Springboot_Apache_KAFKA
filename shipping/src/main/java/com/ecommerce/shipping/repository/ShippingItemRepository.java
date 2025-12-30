package com.ecommerce.shipping.repository;


import com.ecommerce.shipping.model.ShippingItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingItemRepository extends JpaRepository<ShippingItem, Long> {
    List<ShippingItem> findByShippingId(Long shippingId);
}
