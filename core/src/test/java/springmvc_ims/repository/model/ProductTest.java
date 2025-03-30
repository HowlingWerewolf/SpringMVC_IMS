package springmvc_ims.repository.model;

import junit.framework.TestCase;
import springmvc_ims.repository.model.Product;

public class ProductTest extends TestCase {

    private Product product;

    protected void setUp() throws Exception {
        product = new Product();
    }

    public void testSetDescription() {        
        String testDescription = "aDescription";
        product.setDescription(testDescription);
        assertEquals(testDescription, product.getDescription());
    }

    public void testSetAndGetPrice() {
        double testPrice = 100.00;  
        product.setPrice(testPrice);
        assertEquals(testPrice, product.getPrice(), 0);
    }
  
}