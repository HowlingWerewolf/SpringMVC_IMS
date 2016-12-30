package springmvc_ims.web;

import junit.framework.TestCase;
import springmvc_ims.dao.testing.InMemoryProductDao;
import springmvc_ims.service.SimpleProductManager;
import springmvc_ims.web.mock.MockModel;

public class PriceIncreaseFormControllerTest extends TestCase {

    public void testDisplayLogin() throws Exception {
        PriceIncreaseFormController controller = new PriceIncreaseFormController();
        SimpleProductManager manager = new SimpleProductManager();
        MockModel model = new MockModel();  
        
        manager.setProductDao(new InMemoryProductDao(null));
        controller.setProductManager(manager);
        assertEquals("priceincrease", controller.displayLogin(model));
    }
    
}