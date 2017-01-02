package springmvc_ims.web.controller;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;
import springmvc_ims.repository.dao.testing.InMemoryProductDao;
import springmvc_ims.service.SimpleProductManager;
import springmvc_ims.web.controller.InventoryController;

public class InventoryControllerTest extends TestCase {

    @SuppressWarnings("rawtypes")
	public void testHandleRequestView() throws Exception {
        InventoryController controller = new InventoryController();
        SimpleProductManager manager = new SimpleProductManager();
        manager.setProductDao(new InMemoryProductDao(null));
        controller.setProductManager(manager);
        ModelAndView modelAndView = controller.handleRequest(null, null);
        assertEquals("hello", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
        Map modelMap = (Map) modelAndView.getModel().get("model");
        String nowValue = (String) modelMap.get("now");
        assertNotNull(nowValue);
    }
    
}