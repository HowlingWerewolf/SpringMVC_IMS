package springmvc_ims.web.controller;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;
import springmvc_ims.service.ProductService;

public class InventoryControllerTest extends TestCase {
	
	@InjectMocks
	private InventoryController controller;
	
	@Mock
	private ProductService productService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(productService.getProducts()).thenReturn(new ArrayList<>());
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
    }
    
}