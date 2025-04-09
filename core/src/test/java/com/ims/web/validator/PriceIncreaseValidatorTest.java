package com.ims.web.validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import junit.framework.TestCase;
import com.ims.web.form.PriceIncrease;

public class PriceIncreaseValidatorTest extends TestCase {
	
	@InjectMocks
	private PriceIncreaseValidator priceIncreaseValidator;
	
	private PriceIncrease priceIncrease;
	private Errors errors;

	@Before
    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
    	priceIncrease = new PriceIncrease();
    	errors = new BeanPropertyBindingResult(priceIncrease, "percentage");
    }

	@Test
    public void testValidateNull() {
    	priceIncrease = null;
    	priceIncreaseValidator.validate(priceIncrease, errors);
    	assertEquals("Value required.", getErrorMessage()); 
    }

	@Test
    public void testValidateTooLow() {
    	priceIncrease.setPercentage(0);
    	priceIncreaseValidator.validate(priceIncrease, errors);
    	assertEquals("Value too low.", getErrorMessage());    	
    }

	@Test
    public void testValidateTooHigh() {
    	priceIncrease.setPercentage(100);
    	priceIncreaseValidator.validate(priceIncrease, errors);
    	assertEquals("Value too high.", getErrorMessage()); 
    }
    
    private String getErrorMessage() {
    	return (String) errors.getFieldError("percentage").getDefaultMessage();
    }

}
