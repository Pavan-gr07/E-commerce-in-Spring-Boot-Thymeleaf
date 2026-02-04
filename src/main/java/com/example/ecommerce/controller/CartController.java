package com.example.ecommerce.controller;


import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String viewCart(Model model){
        model.addAttribute("cartItems",cartService.getCartItems());
        model.addAttribute("total",cartService.getTotalAmount());
        return "user/cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session){

        Map<Long,CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");

        if(cart == null){
            cart = new HashMap<>();
        }

        cartService.addToCart(id);

        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id){
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }


}
