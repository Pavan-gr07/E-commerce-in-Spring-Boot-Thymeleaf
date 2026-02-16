package com.example.ecommerce.controller;

import com.example.ecommerce.model.User;
import com.example.ecommerce.service.OrderService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String listOrders(Model model, HttpSession session) {
        User user = (User) session.getAttribute("loggedUser");

        if (user == null || !user.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }

        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/order/list";
    }

    @GetMapping("/{id}")
    public String vieworder(@PathVariable Long id, Model model, HttpSession session) {

        User user = (User) session.getAttribute("loggedUser");

        if (user == null || !user.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }

        model.addAttribute("order", orderService.getOrderById(id));
        return "admin/order/view";
    }

    /// update order status
    @PostMapping("/update-status")
    public String updateOrderStatus(
            @RequestParam Long orderId,
            @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/admin/orders";
    }

}
