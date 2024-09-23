package com.example.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CategoryTest {

    @Test
    public void testEnumValues() {
        // Check that the enum constants are correct
        assertThat(Category.ELECTRONICS).isEqualTo(Category.valueOf("ELECTRONICS"));
        assertThat(Category.FURNITURE).isEqualTo(Category.valueOf("FURNITURE"));
        assertThat(Category.CLOTHING).isEqualTo(Category.valueOf("CLOTHING"));
        assertThat(Category.TOYS).isEqualTo(Category.valueOf("TOYS"));
    }

    @Test
    public void testEnumCount() {
        // Check that the number of enum constants is as expected
        assertThat(Category.values()).hasSize(4); // Adjust the count if you have more or fewer enums
    }

    @Test
    public void testEnumName() {
        // Verify the name of an enum constant
        assertThat(Category.ELECTRONICS.name()).isEqualTo("ELECTRONICS");
    }
}
