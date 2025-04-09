package com.ims.web.controller;

import jakarta.servlet.ServletException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import com.ims.service.ProductService;
import com.ims.web.form.PriceIncrease;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PriceIncreaseFormControllerTest extends TestCase {
	
	@InjectMocks
	private PriceIncreaseFormController controller;
	
	@Mock
	private ProductService productService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testPriceincreaseError() throws ServletException {
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(true);
		assertNull(controller.onSubmit(new PriceIncrease(), result));
	}
	
	@Test
	public void testPriceincrease() throws ServletException {	
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(false);
		PriceIncrease priceIncrease = new PriceIncrease();
		priceIncrease.setPercentage(5);
		assertNotNull(controller.onSubmit(priceIncrease, result));
	}
	
	@Test
	public void testFormBackingObject() throws ServletException {	
		assertNotNull(controller.formBackingObject(null));
	}
	
	@Test
	public void testDisplayLogin() throws ServletException {	
		Model model = mock(Model.class);
		assertTrue("priceincrease".equals(controller.displayLogin(model)));
	}
    
}