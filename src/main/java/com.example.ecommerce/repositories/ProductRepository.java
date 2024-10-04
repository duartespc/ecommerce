package com.example.ecommerce.repositories;

import com.example.ecommerce.domain.product.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {}
