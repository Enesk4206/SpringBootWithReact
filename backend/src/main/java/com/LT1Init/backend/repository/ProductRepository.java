package com.LT1Init.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LT1Init.backend.model.Product;

public interface  ProductRepository extends JpaRepository<Product, Long> {
    
}
