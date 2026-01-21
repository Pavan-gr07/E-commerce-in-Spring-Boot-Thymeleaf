package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {

    Category saveCategory(Category category);

    Category updateCategory(Category category);

    Category getCategoryById(long id);

    void deleteCategory(Category category);

    List<Category> getAllCategories();


    void deleteCategoryById(long id);
}
