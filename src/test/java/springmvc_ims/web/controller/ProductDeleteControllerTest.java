package springmvc_ims.web.controller;

import org.springframework.web.servlet.ModelAndView;

import junit.framework.TestCase;
import springmvc_ims.repository.dao.testing.InMemoryProductDaoImpl;
import springmvc_ims.service.ProductService;
import springmvc_ims.web.controller.ProductDeleteController;
import springmvc_ims.web.mock.MockModel;

public class ProductDeleteControllerTest extends TestCase {

    /*public void testDisplayLogin() throws Exception {
        ProductDeleteController controller = new ProductDeleteController();
        ProductService manager = new ProductService();
        MockModel model = new MockModel();          
        manager.setProductDao(new InMemoryProductDaoImpl(null));
        controller.setProductManager(manager);
        ModelAndView modelAndView = controller.displayLogin(model);
        assertEquals("productdelete", modelAndView.getViewName());
        assertNotNull(modelAndView.getModel());
    }*/
    
}