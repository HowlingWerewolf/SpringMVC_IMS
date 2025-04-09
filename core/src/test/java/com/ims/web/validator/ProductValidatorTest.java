package com.ims.web.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.ims.repository.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductValidatorTest {
	
	@InjectMocks
	private ProductValidator productValidator;
	
	private Product product;
	private Errors errors;
    
	@BeforeEach
    void setUp() {
		MockitoAnnotations.initMocks(this);
    	product = new Product();
    }

	@Test
    public void testvalidateNoDescription() {
    	product.setId(1);
    	product.setDescription("");
    	product.setPrice(111d);
    	errors = new BeanPropertyBindingResult(product, "description");
    	productValidator.validate(product, errors);
    	assertEquals("Value required.", getDescriptionErrorMessage());    	
    }

	@Test
    void testValidateNoPrice() {
    	product.setId(1);
    	product.setDescription("aaa");
    	product.setPrice(null);
    	errors = new BeanPropertyBindingResult(product, "price");
    	productValidator.validate(product, errors);
    	assertEquals("Value required.", getPriceErrorMessage()); 
    }

	@Test
    void testValidateTooLowPrice() {
    	product.setId(1);
    	product.setDescription("aaa");
    	product.setPrice(-100d);
    	errors = new BeanPropertyBindingResult(product, "price");
    	productValidator.validate(product, errors);
    	assertEquals("Value too low.", getPriceErrorMessage()); 
    }
    
    private String getDescriptionErrorMessage() {
    	try {
    		if (errors.getFieldError("description").getDefaultMessage() != null) {
    			return errors.getFieldError("description").getDefaultMessage();
    		}
    	} catch (NullPointerException ex) {}
    	
    	return "";
    }
    
    private String getPriceErrorMessage() {
    	try {
    		if (errors.getFieldError("price").getDefaultMessage() != null) {
    			return errors.getFieldError("price").getDefaultMessage();
    		}
    	} catch (NullPointerException ignored) {}
    	
    	return "";
    }

}
