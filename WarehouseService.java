package com.example.service;

import com.example.entities.Category;
import com.example.entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class WarehouseService {
    private final Lock lock = new ReentrantLock();
    private final Warehouse warehouse;

    public WarehouseService() {
        this.warehouse = new Warehouse();
        initializeSampleProducts();
    }

    private void initializeSampleProducts() {
        warehouse.addProduct(new Product(1, "Smartphone", Category.ELECTRONICS, 3, LocalDate.now(), LocalDate.now()));
        warehouse.addProduct(new Product(2, "T-Shirt", Category.CLOTHING, 4, LocalDate.now(), LocalDate.now()));
        warehouse.addProduct(new Product(3, "Table", Category.FURNITURE, 3, LocalDate.now(), LocalDate.now()));
        warehouse.addProduct(new Product(4, "Firetruck car", Category.TOYS, 5, LocalDate.now(), LocalDate.now()));
    }

    public void addProduct(Product product) {
        lock.lock();
        try {
            warehouse.addProduct(product);
        } finally {
            lock.unlock();
        }
    }

    public void updateProduct(int id, String name, Category category, int rating) {
        lock.lock();
        try {
            warehouse.updateProduct(id, name, category, rating); // Change String id to int id
        } finally {
            lock.unlock();
        }
    }

    public List<Product> fetchAllProducts(int page, int size) {
        lock.lock();
        try {
            int offset = (page - 1) * size;
            return warehouse.getAllProducts().stream()
                    .skip(offset)
                    .limit(size)
                    .collect(Collectors.toList());
        } finally {
            lock.unlock();
        }
    }

    public Optional<Product> fetchProductById(int id) {
        lock.lock();
        try {
            return warehouse.getProductById(id);
        } finally {
            lock.unlock();
        }
    }

    public List<Product> fetchProductsByCategory(Category category) {
        lock.lock();
        try {
            return warehouse.getProductsByCategory(category);
        } finally {
            lock.unlock();
        }
    }

    public List<Product> fetchProductsCreatedAfter(LocalDate afterDate) {
        lock.lock();
        try {
            return warehouse.getRecentlyCreatedProducts(afterDate);
        } finally {
            lock.unlock();
        }
    }

    public List<Product> fetchModifiedProducts() {
        lock.lock();
        try {
            return warehouse.getUpdatedProducts();
        } finally {
            lock.unlock();
        }
    }
}
