package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public UserController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("products",productService.getAllProducts());
        model.addAttribute("categories",categoryService.getAllCategories());
        return "user/home";
    }


    @GetMapping("/category/{id}")
    public String productByCategory(@PathVariable Long id, Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        model.addAttribute("products",productService.getProductsByCategory(id));
        return "user/home";
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Long id, Model model){
        model.addAttribute("product",productService.getProductById(id));
        return "user/product";
    }

}
