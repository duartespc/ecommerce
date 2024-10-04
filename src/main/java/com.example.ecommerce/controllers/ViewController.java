package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.category.Category;
import com.example.ecommerce.repositories.CategoryRepository;
import com.example.ecommerce.repositories.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;


/**
 * Controller for the home page.
 */
@Controller
public class ViewController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ViewController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal User principal) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "index.html";
    }

    @GetMapping("/store")
    public String store(Model model, @AuthenticationPrincipal User principal) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "store.html";
    }

    @GetMapping("/categories")
    public String categories(Model model, @AuthenticationPrincipal User principal) {
        List<Category> categories = categoryRepository.findAll().stream()
                .filter(category -> category.getParent_id() == null) // Assuming root categories have no parent
                .toList();

        model.addAttribute("categories", categories);
        return "categories.html";
    }

    @GetMapping({"categories/**"})
    public String getCategoryByPath(Model model, @AuthenticationPrincipal User principal, HttpServletRequest request) {
        // Get the full path after /categories/
        String path = URLDecoder.decode(request.getRequestURI().substring("/categories/".length()), StandardCharsets.UTF_8);

        if (path.startsWith("img/") || path.startsWith("js/") || path.startsWith("css/")) {
            System.out.println("The path starts with img/ or js/ or /css");
        } else {

            // Split the path into individual category names
            String[] categoryNames = path.split("/");

            model.addAttribute("parentCategoryName", path);

            // Start from the root categories
            List<Category> categories = categoryRepository.findAll().stream()
                    .filter(category -> category.getParent_id() == null) // Assuming root categories have no parent
                    .toList();

            Category currentCategory = null;

            // Traverse through the category hierarchy based on the names in the URL
            for (String name : categoryNames) {
                Optional<Category> categoryOpt = categories.stream()
                        .filter(c -> c.getName().equalsIgnoreCase(name))
                        .findFirst();

                if (categoryOpt.isEmpty()) {
                    return "redirect:/store"; // If the category is not found, redirect to store
                }

                currentCategory = categoryOpt.get();
                categories = currentCategory.getSubcategories();

                // If no subcategories, redirect to the store or render the current category page
                if (categories.isEmpty()) {
                    return "redirect:/store";
                }
            }

            model.addAttribute("categories", categories);
        }
        return "categories.html";
    }

}
