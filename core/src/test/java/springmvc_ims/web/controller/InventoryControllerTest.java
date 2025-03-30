package springmvc_ims.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;
import springmvc_ims.repository.model.Product;
import springmvc_ims.service.ProductService;

public class InventoryControllerTest extends TestCase {
	
	@InjectMocks
	private InventoryController controller;
	
	@Mock
	private ProductService productService;
	
	List<Product> mockProducts;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Product p1 = new Product();
		p1.setDescription("mock1");
		p1.setId(1);
		p1.setPrice(1.0d);
		
		Product p2 = new Product();
		p2.setDescription("mock2");
		p2.setId(2);
		p2.setPrice(2.0d);
		
		mockProducts = new ArrayList<>();		
		mockProducts.add(p1);
		mockProducts.add(p2);
		
		Mockito.when(productService.getProducts()).thenReturn(mockProducts);
	}

    @SuppressWarnings("rawtypes")
    @Test
	public void testHandleRequestView() throws Exception {
        ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("hello", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        
        Map modelMap = (Map) modelAndView.getModel().get("model");
        
        String nowValue = (String) modelMap.get("now");       
        assertNotNull(nowValue);
        
        @SuppressWarnings("unchecked")
		List<Product> productsValue = (List<Product>) modelMap.get("products");
        assertTrue(productsValue.equals(mockProducts));
    }

    @SuppressWarnings("rawtypes")
    @Test
	public void testHandleRequestViewNullModel() throws Exception {
    	Mockito.when(productService.getProducts()).thenReturn(null);
    	
        ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("hello", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        
        Map modelMap = (Map) modelAndView.getModel().get("model");
        
        String nowValue = (String) modelMap.get("now");       
        assertNotNull(nowValue);
                
        @SuppressWarnings("unchecked")
		List<Product> productsValue = (List<Product>) modelMap.get("products");
        assertTrue(productsValue.isEmpty());
    }
    
}