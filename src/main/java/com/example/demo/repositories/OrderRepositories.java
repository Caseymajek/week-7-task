package com.example.demo.repositories;


import com.example.demo.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositories extends JpaRepository<Order, Long> {
}
