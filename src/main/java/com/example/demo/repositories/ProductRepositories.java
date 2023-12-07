package com.example.demo.repositories;

import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepositories extends JpaRepository<Product, Long> {
    List<Product> findByCategories(String categories);
}
