package com.example.entities;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record Product(
        int id,
        @NotEmpty(message = "Product name can't be empty") String name,
        @org.jetbrains.annotations.NotNull Category category,
        @Min(value = 1, message = "Rating must be at least 1") @Max(value = 5, message = "Max-rating is 5") int rating,
        @PastOrPresent LocalDate createdDate,
        @PastOrPresent LocalDate modifiedDate
) {
    public Product {
        // Automatically set modifiedDate if it wasn't passed
        if (modifiedDate == null) {
            modifiedDate = createdDate;
        }
    }

    // Getter methods for createdDate and modifiedDate
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getModifiedDate() {
        return modifiedDate;
    }
}
