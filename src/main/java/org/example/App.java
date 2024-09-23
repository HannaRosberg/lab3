package org.example;

import com.example.service.Warehouse;
import com.example.entities.Product;
import com.example.entities.Category;
import java.time.LocalDateTime;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();

        // Adding products
        Product product1 = new Product(1, "Laptop", Category.ELECTRONICS, 8);
        Product product2 = new Product(2, "Smartphone", Category.ELECTRONICS, 9);
        Product product3 = new Product(3, "Chair", Category.FURNITURE, 7, LocalDateTime.now(), LocalDateTime.now().minusDays(1));
        Product product4 = new Product(4, "Table", Category.FURNITURE, 6);
        Product product5 = new Product(5, "Teddy Bear", Category.TOYS, 10);

        warehouse.addProduct(product1);
        warehouse.addProduct(product2);
        warehouse.addProduct(product3);
        warehouse.addProduct(product4);
        warehouse.addProduct(product5);

        System.out.println("All Products: " + warehouse.getAllProducts());

        List<Product> electronics = warehouse.getProductsByCategory(Category.ELECTRONICS);
        System.out.println("Electronics: " + electronics);

        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        List<Product> newProducts = warehouse.getProductsCreatedAfter(oneHourAgo);
        System.out.println("Products created after one hour ago: " + newProducts);

        List<Product> modifiedProducts = warehouse.getModifiedProducts();
        System.out.println("Modified products: " + modifiedProducts);

        warehouse.modifyProduct(2, "Updated Smartphone", Category.ELECTRONICS, 10);
        System.out.println("After modification: " + warehouse.getAllProducts());
    }
}
