package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

//    dependency injection
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    @Override
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }
    @Override
    public Product updateProduct(Product product) {
       return  productRepository.save(product);
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
    }


    @Override
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }


}
