package com.example.SpringBootPractice002.services;

import com.example.SpringBootPractice002.models.Product;
import com.example.SpringBootPractice002.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // create or update a product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // get a product by id
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // delete a product by id
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
