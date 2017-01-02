package springmvc_ims.web;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;
import springmvc_ims.dao.testing.InMemoryProductDao;
import springmvc_ims.web.service.SimpleProductManager;

public class InventoryControllerTest extends TestCase {

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