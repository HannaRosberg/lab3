package com.example.service;

import com.example.entities.Category;
import com.example.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarehouseTest {

    private Warehouse warehouse;

    @BeforeEach
    public void setup() {
        warehouse = new Warehouse();
    }

    @Test
    public void testAddProductSuccessfully() {
        Product product = new Product(1, "Phone", Category.ELECTRONICS, 8, LocalDateTime.now(), LocalDateTime.now());
        warehouse.addProduct(product);

        assertThat(warehouse.getAllProducts()).hasSize(1);
        assertThat(warehouse.getAllProducts().get(0).getName()).isEqualTo("Phone");
    }

    @Test
    public void testAddProductWithEmptyName() {
        Product product = new Product(2, "", Category.FURNITURE, 5);
        assertThatThrownBy(() -> warehouse.addProduct(product))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product name cannot be empty");
    }

    @Test
    public void testModifyProductSuccessfully() {
        Product product = new Product(1, "Phone", Category.ELECTRONICS, 8);
        warehouse.addProduct(product);
        warehouse.modifyProduct(1, "Smartphone", Category.ELECTRONICS, 9);

        Product modifiedProduct = warehouse.getProductById(1);
        assertThat(modifiedProduct.getName()).isEqualTo("Smartphone");
        assertThat(modifiedProduct.getRating()).isEqualTo(9);
    }

    @Test
    public void testGetProductsByCategory() {
        Product product1 = new Product(1, "Phone", Category.ELECTRONICS, 8);
        Product product2 = new Product(2, "Chair", Category.FURNITURE, 5);
        warehouse.addProduct(product1);
        warehouse.addProduct(product2);

        assertThat(warehouse.getProductsByCategory(Category.ELECTRONICS)).containsExactly(product1);
        assertThat(warehouse.getProductsByCategory(Category.FURNITURE)).containsExactly(product2);
    }

    @Test
    public void testGetProductsCreatedAfter() {
        Warehouse warehouse = new Warehouse();

        Product product = new Product(2, "Chair", Category.FURNITURE, 5);
        warehouse.addProduct(product);

        LocalDateTime afterDate = LocalDateTime.now().minusDays(1);
        List<Product> result = warehouse.getProductsCreatedAfter(afterDate);

        assertEquals(1, result.size());
        assertEquals(product, result.getFirst());
    }

    @Test
    public void testGetModifiedProducts() {
        LocalDateTime now = LocalDateTime.now();
        Product product1 = new Product(1, "Phone", Category.ELECTRONICS, 8, now.minusDays(1), now.minusDays(1));
        Product product2 = new Product(2, "Chair", Category.FURNITURE, 5, now.minusDays(1), now);
        warehouse.addProduct(product1);
        warehouse.addProduct(product2);

        assertThat(warehouse.getModifiedProducts()).containsExactly(product2);
    }
}
