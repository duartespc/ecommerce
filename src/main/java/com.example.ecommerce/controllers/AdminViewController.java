package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.category.Category;
import com.example.ecommerce.domain.product.Product;
import com.example.ecommerce.repositories.CategoryRepository;
import com.example.ecommerce.repositories.ProductRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


/**
 * Controller for the home page.
 */
@Controller
@RequestMapping("/admin")
public class AdminViewController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public AdminViewController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/")
    public String admin(Model model, @AuthenticationPrincipal User principal) {
        return "admin/index.html";
    }

    @GetMapping("/products")
    public String getProducts(Model model, @AuthenticationPrincipal User principal) {
        model.addAttribute("products", productRepository.findAll());

        return "admin/products.html";
    }

    @GetMapping("/products/add")
    public String addProducts(Model model, @AuthenticationPrincipal User principal) {
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("title", "Add Product");
        model.addAttribute("categories", categoryRepository.findAll());

        return "admin/productDetail.html";
    }

    @GetMapping("/products/edit/{id}")
    public String editProducts(Model model, @AuthenticationPrincipal User principal, @PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            model.addAttribute("title", "Edit Product");
            model.addAttribute("categories", categoryRepository.findAll());

        }
        return "admin/productDetail.html";
    }

    @GetMapping("/categories")
    public String getCategories(Model model, @AuthenticationPrincipal User principal) {
        model.addAttribute("categories", categoryRepository.findAll());

        return "admin/categories.html";
    }

    @GetMapping("/categories/add")
    public String addCategory(Model model, @AuthenticationPrincipal User principal) {
        Category category = new Category();
        model.addAttribute("category", category);
        model.addAttribute("title", "Add Category");
        model.addAttribute("categories", categoryRepository.findAll());

        return "admin/categoryDetail.html";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategory(Model model, @AuthenticationPrincipal User principal, @PathVariable int id) {
        Optional<Category> category = categoryRepository.findById(id);

        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            model.addAttribute("title", "Edit Category");
            model.addAttribute("categories", categoryRepository.findAll());

        }
        return "admin/categoryDetail.html";
    }

}
