package com.ecommerce.cart.controller;

import com.ecommerce.cart.model.AddToCartRequest;
import com.ecommerce.cart.model.CartItem;
import com.ecommerce.cart.model.CartItemDto;
import com.ecommerce.cart.model.UpdateQuantityRequest;
import com.ecommerce.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ecommerce/cart")
public class CartController {
    private final CartService service;

    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/items")
    public ResponseEntity<Void> addToCart(@RequestBody AddToCartRequest request, Authentication authentication){
        Long userId=Long.valueOf(authentication.getName());
        service.addToCart(userId,request);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItemDto>> getItems(Authentication authentication){
        Long userId=Long.valueOf(authentication.getName());
        List<CartItemDto> cartItems= service.getItems(userId);
        return ResponseEntity.ok(cartItems);

    }
    @DeleteMapping("/deleteById/{productId}")
    public ResponseEntity<Void> deleteById(@PathVariable("productId") Long productId,  Authentication authentication){
        Long userId=Long.valueOf(authentication.getName());
        service.deleteByUserIdAndProductId(userId, productId);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/clear")
    public ResponseEntity<Void> cartClear(Authentication authentication){
        Long userId=Long.valueOf(authentication.getName());
        service.cartClear(userId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/items/{productId}")
    public ResponseEntity<Void> updateQuantity(
            @PathVariable ("productId") Long productId,
            @RequestBody UpdateQuantityRequest request,
            Authentication authentication){

        Long userId=Long.valueOf(authentication.getName());
        service.updateQuantity(userId, productId, request.getUpdateAction());

        return ResponseEntity.noContent().build();
    }


}
