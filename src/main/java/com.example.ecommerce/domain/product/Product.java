package com.example.ecommerce.domain.product;

import com.example.ecommerce.domain.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private double price;
    private int discount;

    @Lob
    private String image;

    private String manufacturer;
    private String reference;
    private double weight;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private Category category;

    @ElementCollection
    private List<Integer> rating;

    // No-arg constructor initializing rating
    public Product() {
        this.rating = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0)); // Initialize with default ratings
    }

}
