package com.ims.web.service;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import com.ims.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
class SimpleProductManagerTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductDaoImpl productDaoImpl;

    private List<Product> products;

    private static final int PRODUCT_COUNT = 2;

    private static final Double CHAIR_PRICE = 20.50d;
    private static final String CHAIR_DESCRIPTION = "Chair";

    private static final String TABLE_DESCRIPTION = "Table";
    private static final Double TABLE_PRICE = 150.10d;

    private static final int POSITIVE_PRICE_INCREASE = 10;

    @BeforeEach
    void setUp() {
        // stub up a list of products
        products = List.of(
                Product.builder()
                        .description("Chair")
                        .price(CHAIR_PRICE)
                        .build(),
                Product.builder()
                        .description("Table")
                        .price(TABLE_PRICE).build()
        );
    }

    @Test
    void testGetProductsWithNoProducts() {
        assertTrue(productService.getProducts().isEmpty());
    }

    @Test
    void testGetProducts() {
        Mockito.when(productDaoImpl.queryAllAsList()).thenReturn(products);

        final List<Product> products = productService.getProducts();
        assertNotNull(products);
        assertEquals(PRODUCT_COUNT, productService.getProducts().size());

        final Product product1 = products.get(0);
        assertEquals(CHAIR_DESCRIPTION, product1.getDescription());
        assertEquals(CHAIR_PRICE, product1.getPrice());

        final Product product2 = products.get(1);
        assertEquals(TABLE_DESCRIPTION, product2.getDescription());
        assertEquals(TABLE_PRICE, product2.getPrice());
    }

    @Test
    void testIncreasePriceWithNullListOfProducts() {
        try {
            productService.increasePrice(POSITIVE_PRICE_INCREASE);
        } catch (NullPointerException ex) {
            fail("Products list is null.");
        }
    }

    @Test
    void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productService.increasePrice(POSITIVE_PRICE_INCREASE);
        } catch (Exception ex) {
            fail("Products list is empty.");
        }
    }

    @Test
    void testIncreasePriceWithPositivePercentage() {
        Mockito.when(productDaoImpl.queryAllAsList()).thenReturn(products);

        productService.increasePrice(POSITIVE_PRICE_INCREASE);
        double expectedChairPriceWithIncrease = 22.55;
        double expectedTablePriceWithIncrease = 165.11;

        final List<Product> products = productService.getProducts();
        assertEquals(expectedChairPriceWithIncrease, products.get(0).getPrice());
        assertEquals(expectedTablePriceWithIncrease, products.get(1).getPrice());
    }

}
