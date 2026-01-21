package com.example.ecommerce.controller;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.CategoryService;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class AdminController {


    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminController (ProductService productService, CategoryService categoryService) {
        this.categoryService =  categoryService;
        this.productService = productService;
    }

    // Show add product form
    @GetMapping("/admin/product/add")
    public String showAddProductForm(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product/add";
    }

    @GetMapping("/admin/products")
    public String viewAllProducts(Model model){
        model.addAttribute("products", productService.getAllProducts());
        return "admin/product/list";
    }

    // Handle form submit

    @PostMapping("/admin/product/save")
    public String saveProduct(
            @Valid Product product,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "admin/product/add";
        }

        productService.saveProduct(product);
        return "redirect:/admin/products";
    }


    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String showEditProductForm(@PathVariable long id, Model model) {

        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "admin/product/edit";
    }

    @PostMapping("/admin/product/update")
    public String updateProduct(
            @Valid Product product,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "admin/product/edit";
        }

        productService.updateProduct(product);
        return "redirect:/admin/products";
    }




}
