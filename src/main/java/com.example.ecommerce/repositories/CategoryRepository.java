package com.example.ecommerce.repositories;

import com.example.ecommerce.domain.category.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    List<Category> findAll();
    Optional<Category> findByName(String name);
}
