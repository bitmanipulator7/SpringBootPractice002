package com.example.SpringBootPractice002.services;

import com.example.SpringBootPractice002.exceptions.ProductNotFoundException;
import com.example.SpringBootPractice002.models.Product;
import com.example.SpringBootPractice002.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    // get a product by id
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    // delete a product by id
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
