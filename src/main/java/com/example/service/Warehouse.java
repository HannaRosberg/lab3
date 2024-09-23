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

    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // Return a copy to ensure immutability
    }

    public Product getProductById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Product> getProductsByCategory(Category category) {
        return products.stream()
                .filter(p -> p.getCategory() == category)
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsCreatedAfter(LocalDateTime date) {
        return products.stream()
                .filter(p -> p.getCreatedDate().isAfter(date))
                .collect(Collectors.toList());
    }

    public List<Product> getModifiedProducts() {
        return products.stream()
                .filter(p -> !p.getCreatedDate().equals(p.getLastModifiedDate()))
                .collect(Collectors.toList());
    }
}
