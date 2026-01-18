package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    Product getProductById(long id);
    void deleteProductById(long id);

}

