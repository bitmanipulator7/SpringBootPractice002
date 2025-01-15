package com.example.SpringBootPractice002.repositories;

import com.example.SpringBootPractice002.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
