package com.example.demo.repositories;

import com.example.demo.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepositories extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
