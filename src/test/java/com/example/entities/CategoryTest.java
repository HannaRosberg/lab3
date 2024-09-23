package com.example.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CategoryTest {

    @Test
    public void testEnumValues() {
        assertThat(Category.ELECTRONICS).isEqualTo(Category.valueOf("ELECTRONICS"));
        assertThat(Category.FURNITURE).isEqualTo(Category.valueOf("FURNITURE"));
        assertThat(Category.CLOTHING).isEqualTo(Category.valueOf("CLOTHING"));
        assertThat(Category.TOYS).isEqualTo(Category.valueOf("TOYS"));
    }

    @Test
    public void testEnumCount() {
        assertThat(Category.values()).hasSize(4);
    }

    @Test
    public void testEnumName() {
        assertThat(Category.ELECTRONICS.name()).isEqualTo("ELECTRONICS");
    }
}
