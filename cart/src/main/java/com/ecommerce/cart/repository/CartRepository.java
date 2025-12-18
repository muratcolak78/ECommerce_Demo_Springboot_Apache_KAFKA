package com.ecommerce.cart.repository;

import com.ecommerce.cart.model.CartItem;
import com.ecommerce.cart.model.CartItemDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);
    List<CartItem> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
