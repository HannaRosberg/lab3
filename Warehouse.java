package com.example.service;

import com.example.entities.Category;
import com.example.entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(int id, String name, Category category, int rating) {
        products.stream()
                .filter(p -> p.id() == id) // Use == for int comparison
                .findFirst()
                .ifPresentOrElse(existingProduct -> {
                    // Use index for modification
                    Product updated = new Product(id, name, category, rating, existingProduct.getCreatedDate(), LocalDate.now());
                    int index = products.indexOf(existingProduct);
                    products.set(index, updated);
                }, () -> {
                    throw new IllegalArgumentException("Product with id " + id + " does not exist");
                });
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);  // Return a copy of the list
    }

    public Optional<Product> getProductById(int id) {
        return products.stream().filter(p -> p.id() == id).findFirst(); // Use == for int comparison
    }

    public List<Product> getProductsByCategory(Category category) {
        return products.stream()
                .filter(p -> p.category() == category) // Use == for Category comparison
                .sorted(Comparator.comparing(Product::name))
                .collect(Collectors.toList());
    }

    public List<Product> getRecentlyCreatedProducts(LocalDate afterDate) {
        return products.stream()
                .filter(p -> p.createdDate().isAfter(afterDate))
                .collect(Collectors.toList());
    }

    public List<Product> getUpdatedProducts() {
        return products.stream()
                .filter(p -> !p.getCreatedDate().equals(p.getModifiedDate()))
                .collect(Collectors.toList());
    }
}
