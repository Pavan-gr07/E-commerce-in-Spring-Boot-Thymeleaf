package com.example.ecommerce.controller;


import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

private final CategoryService categoryService;

public AdminCategoryController (CategoryService categoryService){
        this.categoryService = categoryService;
}
    // 1️⃣ LIST CATEGORIES
    @GetMapping
    public String listCategories(Model model){
    model.addAttribute("categories",categoryService.getAllCategories());
    return "admin/category/list";
    }

    //Show Add Form
    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/add";
    }

    //Save Category
    @PostMapping("/save")
    public String saveCategory(@Valid Category category, BindingResult result){
        if(result.hasErrors()){
            return "admin/category/add";
        }

        categoryService.saveCategory(category);
        return "redirect:/admin/categories";
    }

    // 4️⃣ SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable long id,Model model){
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    // 5️⃣ UPDATE CATEGORY
    @PostMapping("/update")
    public String updateCategory(@Valid Category category, BindingResult result){

        if (result.hasErrors()) {
            return "admin/category/edit";
        }

        categoryService.updateCategory(category);
        return "redirect:/admin/categories";
    }

}
