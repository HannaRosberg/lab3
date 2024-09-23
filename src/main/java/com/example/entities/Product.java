package com.example.entities;

import java.time.LocalDateTime;

public class Product {
    private final int id;
    private String name;
    private Category category;
    private int rating;
    private final LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public Product(int id, String name, Category category, int rating, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.createdDate = createdDate != null ? createdDate : LocalDateTime.now();
        this.lastModifiedDate = lastModifiedDate != null ? lastModifiedDate : this.createdDate;
    }

    public Product(int id, String name, Category category, int rating) {
        this(id, name, category, rating, LocalDateTime.now(), LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
        this.lastModifiedDate = LocalDateTime.now();
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", rating=" + rating +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
