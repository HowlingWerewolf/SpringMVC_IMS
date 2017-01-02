package springmvc_ims.web.controller;

import junit.framework.TestCase;
import springmvc_ims.repository.dao.testing.InMemoryProductDao;
import springmvc_ims.service.SimpleProductManager;
import springmvc_ims.web.controller.ProductAddFormController;
import springmvc_ims.web.mock.MockModel;

public class ProductAddFormControllerTest extends TestCase {

    public void testDisplayLogin() throws Exception {
        ProductAddFormController controller = new ProductAddFormController();
        SimpleProductManager manager = new SimpleProductManager();
        MockModel model = new MockModel();  
        
        manager.setProductDao(new InMemoryProductDao(null));
        controller.setProductManager(manager);
        assertEquals("productadd", controller.displayLogin(model));
    }
    
}