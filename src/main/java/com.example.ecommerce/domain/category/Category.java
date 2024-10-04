package com.example.ecommerce.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    @JsonIgnore
    private Category  parent_id;

    // Children categories
    @OneToMany(mappedBy = "parent_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> subcategories = new ArrayList<>();

    @Lob
    private String image;
}
