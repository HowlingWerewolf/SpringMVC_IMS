package com.ims.web.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import com.ims.service.ProductService;
import org.mockito.junit.jupiter.MockitoExtension;

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
    	MockitoAnnotations.initMocks(this);
    	
        products = new ArrayList<Product>();
        
        // stub up a list of products
        Product product = new Product();
        product.setDescription("Chair");
        product.setPrice(CHAIR_PRICE);
        products.add(product);
        
        product = new Product();
        product.setDescription("Table");
        product.setPrice(TABLE_PRICE);
        products.add(product);
    }

    @Test
    void testGetProductsWithNoProducts() {
        assertTrue(productService.getProducts().isEmpty());
    }
    
    @Test
    void testGetProducts() {
    	Mockito.when(productDaoImpl.queryAllAsList()).thenReturn(products);
    	
        List<Product> products = productService.getProducts();
        assertNotNull(products);
        assertEquals(PRODUCT_COUNT, productService.getProducts().size());
    
        Product product = products.get(0);
        assertEquals(CHAIR_DESCRIPTION, product.getDescription());
        assertEquals(CHAIR_PRICE, product.getPrice());
        
        product = products.get(1);
        assertEquals(TABLE_DESCRIPTION, product.getDescription());
        assertEquals(TABLE_PRICE, product.getPrice());      
    }   
    
    @Test
    void testIncreasePriceWithNullListOfProducts() {
        try {
            productService.increasePrice(POSITIVE_PRICE_INCREASE);
        } catch(NullPointerException ex) {
            fail("Products list is null.");
        }
    }
    
    @Test
    void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productService.increasePrice(POSITIVE_PRICE_INCREASE);
        } catch(Exception ex) {
            fail("Products list is empty.");
        }           
    }
    
    @Test
    void testIncreasePriceWithPositivePercentage() {
    	Mockito.when(productDaoImpl.queryAllAsList()).thenReturn(products);
    	
        productService.increasePrice(POSITIVE_PRICE_INCREASE);
        double expectedChairPriceWithIncrease = 22.55;
        double expectedTablePriceWithIncrease = 165.11;
        
        List<Product> products = productService.getProducts();      
        Product product = products.get(0);
        assertEquals(expectedChairPriceWithIncrease, product.getPrice());
        
        product = products.get(1);      
        assertEquals(expectedTablePriceWithIncrease, product.getPrice());       
    } 

}
