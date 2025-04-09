package com.ims.web.validator;

import com.ims.web.form.PriceIncrease;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PriceIncreaseValidatorTest {
	
	@InjectMocks
	private PriceIncreaseValidator priceIncreaseValidator;
	
	private PriceIncrease priceIncrease;
	private Errors errors;

	@BeforeEach
    void setUp() {
		MockitoAnnotations.initMocks(this);
    	priceIncrease = new PriceIncrease();
    	errors = new BeanPropertyBindingResult(priceIncrease, "percentage");
    }

	@Test
    void testValidateNull() {
    	priceIncrease = null;
    	priceIncreaseValidator.validate(priceIncrease, errors);
    	assertEquals("Value required.", getErrorMessage()); 
    }

	@Test
    void testValidateTooLow() {
    	priceIncrease.setPercentage(0);
    	priceIncreaseValidator.validate(priceIncrease, errors);
    	assertEquals("Value too low.", getErrorMessage());    	
    }

	@Test
    void testValidateTooHigh() {
    	priceIncrease.setPercentage(100);
    	priceIncreaseValidator.validate(priceIncrease, errors);
    	assertEquals("Value too high.", getErrorMessage()); 
    }
    
    private String getErrorMessage() {
    	return errors.getFieldError("percentage").getDefaultMessage();
    }

}
