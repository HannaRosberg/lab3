package com.example.entities;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductCreation() {
        Product product = new Product(1, "Test Product", Category.ELECTRONICS, 5);
        LocalDateTime createdDate = product.getCreatedDate();
        assertTrue(createdDate.isBefore(LocalDateTime.now().plusSeconds(1)));
        assertTrue(createdDate.isAfter(LocalDateTime.now().minusSeconds(1)));
    }

    @Test
    public void testDefaultDates() {
        Product product = new Product(1, "Test Product", Category.ELECTRONICS, 5);
        LocalDateTime createdDate = product.getCreatedDate();
        LocalDateTime lastModifiedDate = product.getLastModifiedDate();
        assertTrue(createdDate.isBefore(LocalDateTime.now().plusSeconds(1)));
        assertTrue(createdDate.isAfter(LocalDateTime.now().minusSeconds(1)));
        assertEquals(createdDate, lastModifiedDate);
    }

    @Test
    public void testNameUpdate() {
        Product product = new Product(1, "Old Name", Category.ELECTRONICS, 5);
        product.setName("New Name");
        assertEquals("New Name", product.getName());
    }

    @Test
    public void testCategoryUpdate() {
        Product product = new Product(1, "Test Product", Category.ELECTRONICS, 5);
        product.setCategory(Category.FURNITURE);
        assertEquals(Category.FURNITURE, product.getCategory());
    }

    @Test
    public void testRatingUpdate() {
        Product product = new Product(1, "Test Product", Category.ELECTRONICS, 5);
        product.setRating(10);
        assertEquals(10, product.getRating());
    }

    @Test
    public void testLastModifiedDateUpdate() {
        Product product = new Product(1, "Test Product", Category.ELECTRONICS, 5);
        LocalDateTime initialLastModified = product.getLastModifiedDate();

        // Wait a moment to ensure that time has changed
        try {
            Thread.sleep(1000); // Sleep for 1 second
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Modify the product
        product.setName("Updated Product");

        // Check that lastModifiedDate has been updated
        assertTrue(product.getLastModifiedDate().isAfter(initialLastModified));
    }

    @Test
    public void testCreationWithNullDates() {
        Product product = new Product(1, "Test Product", Category.ELECTRONICS, 5, null, null);
        LocalDateTime createdDate = product.getCreatedDate();
        assertTrue(createdDate.isBefore(LocalDateTime.now().plusSeconds(1)));
    }
}
