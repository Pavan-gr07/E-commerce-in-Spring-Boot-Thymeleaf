package com.example.ecommerce.service;

import com.example.ecommerce.model.CartItem;

import java.util.Map;

public interface CartService {

    void addToCart(Long productid);

    void removeFromCart(Long productid);

    Map<Long, CartItem> getCartItems();

    double getTotalAmount();

}
