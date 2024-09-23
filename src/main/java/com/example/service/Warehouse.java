package com.example.service;

import com.example.entities.Product;
import com.example.entities.Category;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Warehouse {
    private final List<Product> products = new ArrayList<>();

    // Add a product
    public void addProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        products.add(product);
    }

    // Modify an existing product
    public void modifyProduct(int id, String name, Category category, int rating) {
        Product product = getProductById(id);
        if (product != null) {
            product.setName(name);
            product.setCategory(category);
            product.setRating(rating);
            product.setLastModifiedDate(LocalDateTime.now());
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    // Get all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // Return a copy to ensure immutability
    }

    // Get product by ID
    public Product getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Get products by category, sorted by name
    public List<Product> getProductsByCategory(Category category) {
        return products.stream()
                .filter(p -> p.getCategory() == category)
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toList());
    }

    // Get products created after a certain date
    public List<Product> getProductsCreatedAfter(LocalDateTime date) {
        return products.stream()
                .filter(p -> p.getCreatedDate().isAfter(date))
                .collect(Collectors.toList());
    }

    // Get products that have been modified since creation
    public List<Product> getModifiedProducts() {
        return products.stream()
                .filter(p -> !p.getCreatedDate().equals(p.getLastModifiedDate()))
                .collect(Collectors.toList());
    }
}
