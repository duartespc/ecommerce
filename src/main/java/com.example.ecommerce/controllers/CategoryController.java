package com.example.ecommerce.controllers;

import com.example.ecommerce.domain.category.Category;
import com.example.ecommerce.repositories.CategoryRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/categories")
    public RedirectView saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Define the directory where the images will be stored
                String uploadDirectory = "src/main/resources/static/uploads/";
                String targetDirectory = "target/classes/static/uploads/";
                // Ensure the directory exists
                File directory = new File(uploadDirectory);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                File tempDirectory = new File(targetDirectory);
                if (!directory.exists()) {
                    tempDirectory.mkdirs();
                }

                // Get the original filename
                String filename = file.getOriginalFilename();
                // Create a path to save the file
                Path path = Paths.get(uploadDirectory + filename);
                // Create a path to save the temp file
                Path tempPath = Paths.get(targetDirectory + filename);

                // Save the file locally so it persists over restart
                Files.write(path, file.getBytes());
                // Save the file in run time folder so it gets available immediatly
                Files.write(tempPath, file.getBytes());


                // Set the image path in the product object
                category.setImage("uploads/" + filename);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle file save error
            }
        }

        categoryRepository.save(category);
        return new RedirectView("/admin/categories");
    }

    @DeleteMapping("/categories")
    public Category deleteCategory(@RequestBody Category category) {
        return categoryRepository.delete(category);
    }

}
