package com.LT1Init.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.LT1Init.backend.dto.ProductRequest;
import com.LT1Init.backend.model.Category;
import com.LT1Init.backend.model.Color;
import com.LT1Init.backend.model.Product;
import com.LT1Init.backend.repository.CategoryRepository;
import com.LT1Init.backend.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductRequest create(ProductRequest request){
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setColors(new HashSet<>());
        
        //Categori için
        Category category = categoryRepository.findByName(request.getCategory()).orElseThrow(
            () -> new RuntimeException("Kategori bulunamadı!")
        );
        product.setCategory(category);

        Product created = productRepository.save(product);

        return new ProductRequest(
            created.getId(),
            created.getName(),
            created.getDescription(),
            created.getPrice(),
            created.getColors().stream().map(Color::getName).collect(Collectors.toSet()),
            created.getCategory().getName()
        );

    }

     public List<ProductRequest> getAllProducts(){
            try {
                 List<Product> products = productRepository.findAll();
            
                return products.stream().map(
                    product -> new ProductRequest(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getColors().stream().map(Color::getName).collect(Collectors.toSet()),
                        product.getCategory().getName()
                    )).collect(Collectors.toList());
            } catch (Exception e) {
                throw  new RuntimeException("Internal Server error");
            }
           
    }

    public ProductRequest update(Long id, ProductRequest request){
        try {
            Product existsProduct = productRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("İstenilen ürün yok! "+ id)
            );

            existsProduct.setName(request.getName());
            existsProduct.setDescription(request.getDescription());
            existsProduct.setPrice(request.getPrice());
            existsProduct.setColors(new HashSet<>());
            
            //Categori için
            Category category = categoryRepository.findByName(request.getCategory()).orElseThrow(
                () -> new RuntimeException("Kategori bulunamadı!")
            );
            existsProduct.setCategory(category);

            Product updated = productRepository.save(existsProduct);

            return new ProductRequest(
            updated.getId(),
            updated.getName(),
            updated.getDescription(),
            updated.getPrice(),
            updated.getColors().stream().map(Color::getName).collect(Collectors.toSet()),
            updated.getCategory().getName()
            );

        } catch (Exception e) {
             throw  new RuntimeException("Internal Server error");
        }
    }

    public String delete(Long id){
        try {
             Product existsProduct = productRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("İstenilen ürün yok! "+ id)
            );

            productRepository.delete(existsProduct);
            return "Ürün Başarıyla Kaldırıldı id: "+ id;

            
        } catch (Exception e) {
            throw  new RuntimeException("Internal Server error");
        }
    }
}
