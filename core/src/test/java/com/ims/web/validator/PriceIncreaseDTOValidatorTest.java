package com.ims.web.validator;

import com.ims.web.dto.PriceIncreaseDTO;
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
class PriceIncreaseDTOValidatorTest {
	
	@InjectMocks
	private PriceIncreaseValidator priceIncreaseValidator;
	
	private PriceIncreaseDTO priceIncreaseDTO;
	private Errors errors;

	@BeforeEach
    void setUp() {
		MockitoAnnotations.initMocks(this);
    	priceIncreaseDTO = new PriceIncreaseDTO();
    	errors = new BeanPropertyBindingResult(priceIncreaseDTO, "percentage");
    }

	@Test
    void testValidateNull() {
    	priceIncreaseDTO = null;
    	priceIncreaseValidator.validate(priceIncreaseDTO, errors);
    	assertEquals("Value required.", getErrorMessage()); 
    }

	@Test
    void testValidateTooLow() {
    	priceIncreaseDTO.setPercentage(0);
    	priceIncreaseValidator.validate(priceIncreaseDTO, errors);
    	assertEquals("Value too low.", getErrorMessage());    	
    }

	@Test
    void testValidateTooHigh() {
    	priceIncreaseDTO.setPercentage(100);
    	priceIncreaseValidator.validate(priceIncreaseDTO, errors);
    	assertEquals("Value too high.", getErrorMessage()); 
    }
    
    private String getErrorMessage() {
    	return errors.getFieldError("percentage").getDefaultMessage();
    }

}
