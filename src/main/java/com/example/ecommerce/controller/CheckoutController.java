package com.example.ecommerce.controller;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.OrderService;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final HttpSession session;

    public CheckoutController(CartService cartService,
            OrderService orderService,
            HttpSession session, OrderRepository orderRepository) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.session = session;
        this.orderRepository = orderRepository;
    }

    // 📄 Show checkout page
    @GetMapping
    public String checkoutPage(Model model) {
        model.addAttribute("total", cartService.getTotalAmount());
        model.addAttribute("order", new Order());
        return "user/checkout";
    }

    // ✅ Place order
    @PostMapping("/place")
    public String placeOrder(HttpSession session) {

        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        User user = (User) session.getAttribute("loggedUser");

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(cartService.getTotalAmount());
        order.setOrderStatus("PLACED");
        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);

        session.removeAttribute("cart");

        return "redirect:/checkout/success";
    }

    // 🎉 Success page
    @GetMapping("/success")
    public String successPage() {
        return "user/success";
    }
}
