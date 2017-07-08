package springmvc_ims.web.controller;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;
import springmvc_ims.repository.dao.testing.InMemoryProductDaoImpl;
import springmvc_ims.service.ProductService;
import springmvc_ims.web.controller.InventoryController;

public class InventoryControllerTest extends TestCase {

    /*@SuppressWarnings("rawtypes")
	public void testHandleRequestView() throws Exception {
        InventoryController controller = new InventoryController();
        ProductService productService = new ProductService();
        productService.setProductDao(new InMemoryProductDaoImpl(null));
        controller.setProductManager(productService);
        ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("hello", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        Map modelMap = (Map) modelAndView.getModel().get("model");
        String nowValue = (String) modelMap.get("now");
        assertNotNull(nowValue);
    }*/
    
}