package com.example.ecommerce.controller;

import com.example.ecommerce.service.CartService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // 🛒 VIEW CART PAGE
    @GetMapping
    public String viewCart(Model model, HttpSession session) {

        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }
        model.addAttribute("cartItems", cartService.getCartItems().values());
        model.addAttribute("total", cartService.getTotalAmount());
        return "user/cart";
    }

    // ➕ ADD PRODUCT TO CART
    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/login"; // Redirect to login if user is not authenticated
        }

        cartService.addToCart(id);
        return "redirect:/cart";
    }

    // ❌ REMOVE PRODUCT FROM CART
    @GetMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }
}
