package com.ims.web.controller;

import com.ims.service.ProductService;
import com.ims.web.form.PriceIncrease;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceIncreaseFormControllerTest {
	
	@InjectMocks
	private PriceIncreaseFormController controller;
	
	@Mock
	private ProductService productService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testPriceincreaseError() {
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(true);
		assertNull(controller.onSubmit(new PriceIncrease(), result));
	}
	
	@Test
	void testPriceincrease() {
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(false);
		PriceIncrease priceIncrease = new PriceIncrease();
		priceIncrease.setPercentage(5);
		assertNotNull(controller.onSubmit(priceIncrease, result));
	}
	
	@Test
	void testFormBackingObject() throws ServletException {
		assertNotNull(controller.formBackingObject(null));
	}
	
	@Test
	void testDisplayLogin() {
		Model model = mock(Model.class);
		assertTrue("priceincrease".equals(controller.displayLogin(model)));
	}
    
}