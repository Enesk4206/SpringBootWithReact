package com.LT1Init.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LT1Init.backend.dto.CategoryRequest;
import com.LT1Init.backend.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all-categories")
    public ResponseEntity<List<CategoryRequest>> getAllCategoriesAPI(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryRequest> createAPI(@RequestBody CategoryRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request));
    }

     @PutMapping("/update/{id}")
    public ResponseEntity<CategoryRequest> updateAPI(@PathVariable Long id, @RequestBody CategoryRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id,request));
    }
     @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryRequest> deleteAPI(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
