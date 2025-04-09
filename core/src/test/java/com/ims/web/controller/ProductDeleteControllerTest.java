package com.ims.web.controller;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import com.ims.service.ProductService;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductDeleteControllerTest {
	
	@InjectMocks
	private ProductDeleteController controller;
	
	@Mock
	private ProductService productService;
	
	@Mock
	private ProductDaoImpl productDao;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testProductDeleteError() throws ServletException {
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(true);
		assertNull(controller.onSubmit(new Product(), result));
	}
	
	@Test
	void testProductDelete() throws ServletException {
		BindingResult result = mock(BindingResult.class);
	    when(result.hasErrors()).thenReturn(false);
	    Product product = new Product();
	    product.setDescription("mock");
	    product.setId(1);
	    product.setPrice(1.0d);
		assertNotNull(controller.onSubmit(product, result));
	}
	
	@Test
	void testFormBackingObject() throws ServletException {
		assertNotNull(controller.formBackingObject(null));
	}
	
	@Test
	void testDisplayLogin() {
		Model model = mock(Model.class);
		assertNotNull("productdelete".equals(controller.displayLogin(model)));
	}
    
}