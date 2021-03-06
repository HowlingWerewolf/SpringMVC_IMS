package springmvc_ims.web.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import junit.framework.TestCase;
import springmvc_ims.repository.dao.ProductDaoImpl;
import springmvc_ims.repository.model.Product;
import springmvc_ims.service.ProductService;

public class ProductDeleteControllerTest extends TestCase {
	
	@InjectMocks
	private ProductDeleteController controller;
	
	@Mock
	private ProductService productService;
	
	@Mock
	private ProductDaoImpl productDao;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testProductDeleteError() throws ServletException {	
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(true);
		assertNull(controller.onSubmit(new Product(), result));
	}
	
	@Test
	public void testProductDelete() throws ServletException {	
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(false);
	    Product product = new Product();
	    product.setDescription("mock");
	    product.setId(1);
	    product.setPrice(1.0d);
		assertNotNull(controller.onSubmit(product, result));
	}
	
	@Test
	public void testFormBackingObject() throws ServletException {	
		assertNotNull(controller.formBackingObject(null));
	}
	
	@Test
	public void testDisplayLogin() throws ServletException {	
		Model model = mock(Model.class);
		assertNotNull("productdelete".equals(controller.displayLogin(model)));
	}
    
}