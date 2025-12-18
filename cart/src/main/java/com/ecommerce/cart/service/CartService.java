package com.ecommerce.cart.service;

import com.ecommerce.cart.model.AddToCartRequest;
import com.ecommerce.cart.model.CartItemDto;
import com.ecommerce.cart.model.UpdateAction;
import com.ecommerce.cart.model.UpdateQuantityRequest;

import java.util.List;

public interface CartService {
    void addToCart(Long userId, AddToCartRequest addToCartRequest);

    List<CartItemDto> getItems(Long userId);

    void deleteByUserIdAndProductId(Long userId, Long productId);

    void cartClear(Long userId);

    void updateQuantity(Long userId, Long productId, UpdateAction action);
}
