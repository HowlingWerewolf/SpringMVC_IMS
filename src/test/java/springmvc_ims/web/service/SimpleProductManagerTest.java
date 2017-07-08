package springmvc_ims.web.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import springmvc_ims.repository.dao.ProductDaoImpl;
import springmvc_ims.repository.dao.testing.InMemoryProductDaoImpl;
import springmvc_ims.repository.model.Product;
import springmvc_ims.service.ProductService;

public class SimpleProductManagerTest extends TestCase {

    private ProductService productService;

    private List<Product> products;
    
    private static int PRODUCT_COUNT = 2;
    
    private static Double CHAIR_PRICE = new Double(20.50);
    private static String CHAIR_DESCRIPTION = "Chair";
    
    private static String TABLE_DESCRIPTION = "Table";
    private static Double TABLE_PRICE = new Double(150.10);         
    
    private static int POSITIVE_PRICE_INCREASE = 10;
    
    /*protected void setUp() throws Exception {
        productService = new ProductService();
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
        
        ProductDaoImpl productDaoImpl = new InMemoryProductDaoImpl(products);
        productService.setProductDao(productDaoImpl);
    }

    public void testGetProductsWithNoProducts() {
        productService = new ProductService();
        productService.setProductDao(new InMemoryProductDaoImpl(null));
        assertNull(productService.getProducts());
    }
    
    public void testGetProducts() {
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
    
    public void testIncreasePriceWithNullListOfProducts() {
        try {
            productService = new ProductService();
            productService.setProductDao(new InMemoryProductDaoImpl(null));
            productService.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch(NullPointerException ex) {
            fail("Products list is null.");
        }
    }
    
    public void testIncreasePriceWithEmptyListOfProducts() {
        try {
            productService = new ProductService();
            productService.setProductDao(new InMemoryProductDaoImpl(new ArrayList<Product>()));
            productService.increasePrice(POSITIVE_PRICE_INCREASE);
        }
        catch(Exception ex) {
            fail("Products list is empty.");
        }           
    }
    
    public void testIncreasePriceWithPositivePercentage() {
        productService.increasePrice(POSITIVE_PRICE_INCREASE);
        double expectedChairPriceWithIncrease = 22.55;
        double expectedTablePriceWithIncrease = 165.11;
        
        List<Product> products = productService.getProducts();      
        Product product = products.get(0);
        assertEquals(expectedChairPriceWithIncrease, product.getPrice());
        
        product = products.get(1);      
        assertEquals(expectedTablePriceWithIncrease, product.getPrice());       
    } */

}
