package com.example.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;

import jakarta.servlet.http.HttpSession;

// What Composition REALLY means

// Composition = “HAS-A strong relationship”
// One class owns another class as a field.

// public class Order {
//     private User user;   // Order HAS-A User
// }

@Controller
public class AuthController {

    private final UserService userService;
    private final HttpSession session;

    public AuthController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    // Registration and login endpoints would go here, using userService to handle
    // logic
    @GetMapping("/register")
    public String registerPage() {
        return "user/register";
        // Return the registration page view
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.registerUser(user);
        return "redirect:/login"; // Redirect to login page after successful registration
    }

    @GetMapping("/login")
    public String loginPage() {
        return "user/login"; // Return the login page view
    }

    @PostMapping("/login")
    public String login(User user) {
        session.setAttribute("user", user); // Store user in session
        return "redirect:/"; // Redirect to home page after successful login
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

}
