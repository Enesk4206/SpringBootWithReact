package com.LT1Init.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.LT1Init.backend.dto.CategoryRequest;
import com.LT1Init.backend.model.Category;
import com.LT1Init.backend.model.Product;
import com.LT1Init.backend.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryRequest create(CategoryRequest request){
       try {
            if(categoryRepository.existsByName(request.getName())){
                throw new RuntimeException("Kategori zaten var!"+request.getName());
            }
          

            Category category = new Category();
            category.setName(request.getName());
            category.setProducts(new HashSet<>());

            Category created = categoryRepository.save(category);

            return new CategoryRequest(
                created.getId(),
                created.getName(),
                created.getProducts().stream().map(Product::getName).collect(Collectors.toSet())
            );
       } catch (RuntimeException e) {
            throw  new RuntimeException("Internal Server error");
       }

    }

    public List<CategoryRequest> getAllCategories(){
       try {
         List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(
            category -> new CategoryRequest(
                category.getId(),
                category.getName(),
                category.getProducts().stream().map(Product::getName).collect(Collectors.toSet())
            )
        ).collect(Collectors.toList());
       } catch (Exception e) {
            throw  new RuntimeException("Internal Server error");
       }
    }


    public CategoryRequest update(Long id, CategoryRequest request){
       try {
         Category existsCategory = categoryRepository.findById(id).orElseThrow(
            ()-> new RuntimeException("Böyle id'li bir kategori yok "+id)
        );

        existsCategory.setName(request.getName());
        existsCategory.setProducts(new HashSet<>());
        Category updated = categoryRepository.save(existsCategory);

            return new CategoryRequest(
                updated.getId(),
                updated.getName(),
                updated.getProducts().stream().map(Product::getName).collect(Collectors.toSet())
            );
    
       } catch (Exception e) {
            throw  new RuntimeException("Internal Server error");
       }
    }

    public String delete(Long id){
        try {
            Category existsCategory = categoryRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Böyle id'li bir kategori yok "+id)
            );

            categoryRepository.delete(existsCategory);
            return "Category başarıyla silindi.";
 
        } catch (Exception e) {
              throw  new RuntimeException("Internal Server error");
        }
    }
}
