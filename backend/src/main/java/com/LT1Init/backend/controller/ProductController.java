package com.LT1Init.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LT1Init.backend.dto.ProductRequest;
import com.LT1Init.backend.service.ProductService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all-products")
    public ResponseEntity<List<ProductRequest>> getAllProductsAPI(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PostMapping("/create")
    public ResponseEntity<ProductRequest> createAPI(@RequestBody ProductRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
    }

     @PutMapping("/update/{id}")
    public ResponseEntity<ProductRequest> updateAPI(@PathVariable Long id, @RequestBody ProductRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id,request));
    }
     @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductRequest> deleteAPI(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
