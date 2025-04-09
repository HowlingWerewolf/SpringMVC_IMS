package com.ims.repository.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @Test
    void testSetDescription() {
        String testDescription = "aDescription";
        product.setDescription(testDescription);
        assertEquals(testDescription, product.getDescription());
    }

    @Test
    void testSetAndGetPrice() {
        double testPrice = 100.00;  
        product.setPrice(testPrice);
        assertEquals(testPrice, product.getPrice(), 0);
    }
  
}