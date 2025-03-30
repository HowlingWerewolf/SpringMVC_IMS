package springmvc_ims.web.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import junit.framework.TestCase;
import springmvc_ims.repository.dao.ProductDaoImpl;
import springmvc_ims.repository.model.Product;
import springmvc_ims.service.ProductService;

public class SimpleProductManagerTest extends TestCase {

	@InjectMocks
    private ProductService productService;
	
	@Mock
    private ProductDaoImpl productDaoImpl;

    private List<Product> products;
    
    private static int PRODUCT_COUNT = 2;
    
    private static Double CHAIR_PRICE = new Double(20.50);
    private static String CHAIR_DESCRIPTION = "Chair";
    
    private static String TABLE_DESCRIPTION = "Table";
    private static Double TABLE_PRICE = new Double(150.10);         
    
    private static int POSITIVE_PRICE_INCREASE = 10;
    
    @Before
    public void setUp() throws Exception {
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
    public void testGetProductsWithNoProducts() {
        assertTrue(productService.getProducts().isEmpty());
    }
    
    @Test
    public void testGetProducts() {
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
    public void testIncreasePriceWithNullListOfProducts() {
        try {
            productService.increasePrice(POSITIVE_PRICE_INCREASE);
        } catch(NullPointerException ex) {
            fail("Products list is null.");
        }
    }
    
    @Test
    public void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productService.increasePrice(POSITIVE_PRICE_INCREASE);
        } catch(Exception ex) {
            fail("Products list is empty.");
        }           
    }
    
    @Test
    public void testIncreasePriceWithPositivePercentage() {
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
