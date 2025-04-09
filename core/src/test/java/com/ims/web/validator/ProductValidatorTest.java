package com.ims.web.validator;

import com.ims.repository.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductValidatorTest {
	
	@InjectMocks
	private ProductValidator productValidator;
	
	private Product product;
	private Errors errors;
    
	@BeforeEach
    void setUp() {
    	product = Product.builder().build();
    }

	@Test
    void testvalidateNoDescription() {
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
			final FieldError description = Objects.requireNonNull(errors.getFieldError("description"));
			if (description.getDefaultMessage() != null) {
    			return description.getDefaultMessage();
    		}
    	} catch (NullPointerException ignored) {}
    	
    	return "";
    }
    
    private String getPriceErrorMessage() {
    	try {
			final FieldError price = Objects.requireNonNull(errors.getFieldError("price"));
			if (price.getDefaultMessage() != null) {
    			return price.getDefaultMessage();
    		}
    	} catch (NullPointerException ignored) {}
    	
    	return "";
    }

}
