package springmvc_ims.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import junit.framework.TestCase;
import springmvc_ims.service.ProductService;

public class PriceIncreaseFormControllerTest extends TestCase {
	
	@InjectMocks
	private PriceIncreaseFormController controller;
	
	@Mock
	private ProductService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testTodo() {		
	}
    
}