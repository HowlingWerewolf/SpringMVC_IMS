package springmvc_ims.web.controller;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import junit.framework.TestCase;
import springmvc_ims.service.ProductService;

public class ProductAddFormControllerTest extends TestCase {
	
	@InjectMocks
	private ProductAddFormController controller;
	
	@Mock
	private ProductService productService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
    
}