package com.LT1Init.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LT1Init.backend.model.Category;

@Repository
public interface  CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByCategoryName(String name);
}
