package com.LT1Init.backend.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Set<String> colors;
    private String category;
}
