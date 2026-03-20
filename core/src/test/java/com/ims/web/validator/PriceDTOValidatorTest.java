package com.ims.web.validator;

import com.ims.web.dto.PriceDTO;
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
class PriceDTOValidatorTest {
	
	@InjectMocks
	private PriceValidator priceValidator;
	
	private PriceDTO priceDTO;
	private Errors errors;

	@BeforeEach
    void setUp() {
		MockitoAnnotations.initMocks(this);
    	priceDTO = new PriceDTO();
    	errors = new BeanPropertyBindingResult(priceDTO, "percentage");
    }

	@Test
    void testValidateNull() {
    	priceDTO = null;
    	priceValidator.validate(priceDTO, errors);
    	assertEquals("Value required.", getErrorMessage()); 
    }

	@Test
    void testValidateTooLow() {
    	priceDTO.setPercentage(0);
    	priceValidator.validate(priceDTO, errors);
    	assertEquals("Value too low.", getErrorMessage());    	
    }

	@Test
    void testValidateTooHigh() {
    	priceDTO.setPercentage(100);
    	priceValidator.validate(priceDTO, errors);
    	assertEquals("Value too high.", getErrorMessage()); 
    }
    
    private String getErrorMessage() {
    	return errors.getFieldError("percentage").getDefaultMessage();
    }

}
