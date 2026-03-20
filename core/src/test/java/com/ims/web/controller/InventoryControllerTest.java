package com.ims.web.controller;

import com.ims.data.model.Product;
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

@ExtendWith(MockitoExtension.class)
class InventoryControllerTest {

    @InjectMocks
    private InventoryController controller;

    @Mock
    private ProductService productService;

    List<Product> mockProducts;

    @BeforeEach
    void setUp() {
        final Product p1 = Product.builder()
                .description("mock1")
                .id(1)
                .price(1.0d)
                .build();

        final Product p2 = Product.builder()
				.description("mock2")
				.id(2)
				.price(2.0d)
				.build();

        mockProducts = List.of(p1, p2);

        Mockito.when(productService.getProducts()).thenReturn(mockProducts);
    }

    @SuppressWarnings("rawtypes")
    @Test
    void testHandleRequestView() {
        final var response = controller.handleRequest();
        assertNotNull(response);
        final var body = response.getBody();
        assertNotNull(body);

        final String nowValue = (String) body.get("now");
        assertNotNull(nowValue);

        @SuppressWarnings("unchecked") final List<Product> productsValue = (List<Product>) body.get("products");
        assertEquals(productsValue, mockProducts);
    }

    @SuppressWarnings("rawtypes")
    @Test
    void testHandleRequestViewNullModel() {
        Mockito.when(productService.getProducts()).thenReturn(null);
        final var response = controller.handleRequest();
        final var body = response.getBody();
        assertNotNull(body);

        final String nowValue = (String) body.get("now");
        assertNotNull(nowValue);

        @SuppressWarnings("unchecked") final List<Product> productsValue = (List<Product>) body.get("products");
        assertTrue(productsValue.isEmpty());
    }

}