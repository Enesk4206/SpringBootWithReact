package com.LT1Init.backend.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private Date createdDateTime;

    //n-t-n
    @ManyToMany
    @JoinTable(
        name="product_colors",
        joinColumns=@JoinColumn(name ="product_id"),
        inverseJoinColumns= @JoinColumn(name= "color_id")
    )
    private Set<Color> colors = new HashSet<>();
    //o-t-n

    @ManyToOne
    private Category category;

    @PrePersist
    protected void onCreate(){
        this.createdDateTime = new Date();
    }
}
