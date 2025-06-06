package com.LT1Init.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LT1Init.backend.model.Color;

public interface ColorRepository extends JpaRepository<Color, Long> {
    
}
