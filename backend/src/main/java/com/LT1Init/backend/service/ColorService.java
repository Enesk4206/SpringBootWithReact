package com.LT1Init.backend.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.LT1Init.backend.dto.ColorRequest;
import com.LT1Init.backend.model.Color;
import com.LT1Init.backend.model.Product;
import com.LT1Init.backend.repository.ColorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ColorService {

    private final ColorRepository colorRepository;

    public ColorRequest create(ColorRequest request){
       try {
            Color color = new Color();
            color.setName(request.getName());
            color.setProducts(new HashSet<>());

            Color created = colorRepository.save(color);

            return new ColorRequest(
                created.getId(),
                created.getName(),
                created.getProducts().stream().map(Product::getName).collect(Collectors.toSet())
            );
       } catch (Exception e) {
            throw new RuntimeException("Internal Server Error");
       }
    }

    public List<ColorRequest> getAllColors(){
      try {
            List<Color> colors = colorRepository.findAll();

            return colors.stream().map(
                color -> new ColorRequest(
                    color.getId(),
                    color.getName(),
                    color.getProducts().stream().map(Product::getName).collect(Collectors.toSet())

            )).collect(Collectors.toList());
      } catch (Exception e) {
            throw new RuntimeException("Internal Server Error");
      }
    }

    public ColorRequest update(Long id, ColorRequest request){
        try {
                
            Color existsColor = colorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Böyle bir renk yok! "+id)
            );

            existsColor.setName(request.getName());
            existsColor.setProducts(existsColor.getProducts());

            Color updated = colorRepository.save(existsColor);

            return new ColorRequest(
                updated.getId(),
                updated.getName(),
                updated.getProducts().stream().map(Product::getName).collect(Collectors.toSet())
            );
        } catch (Exception e) {
             throw new RuntimeException("Internal Server Error");
        }
    }


    public String delete(Long id ){
       try {
             Color existsColor = colorRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Böyle bir renk yok! "+id)
            );

            colorRepository.delete(existsColor);
            return "Mevcut Renk Başarıyla Kaldırıldı id: "+id;
       } catch (Exception e) {
            throw new RuntimeException("Internal Server Error");
       } 
    }
}
