package springmvc_ims.web.validator;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import junit.framework.TestCase;
import springmvc_ims.web.form.PriceIncrease;

public class PriceIncreaseValidatorTest extends TestCase {
	
	private PriceIncreaseValidator priceIncreaseValidator;
	private PriceIncrease priceIncrease;
	private Errors errors;
    
    protected void setUp() throws Exception {
    	priceIncreaseValidator = new PriceIncreaseValidator();
    	priceIncrease = new PriceIncrease();
    	errors = new BeanPropertyBindingResult(priceIncrease, "percentage");
    }

    public void testValidateNull() {
    	priceIncrease = null;
    	priceIncreaseValidator.validate(priceIncrease, errors);
    	assertEquals("Value required.", getErrorMessage()); 
    }

    public void testValidateTooLow() {
    	priceIncrease.setPercentage(0);
    	priceIncreaseValidator.validate(priceIncrease, errors);
    	assertEquals("Value too low.", getErrorMessage());    	
    }

    public void testValidateTooHigh() {
    	priceIncrease.setPercentage(100);
    	priceIncreaseValidator.validate(priceIncrease, errors);
    	assertEquals("Value too high.", getErrorMessage()); 
    }
    
    private String getErrorMessage() {
    	return (String) errors.getFieldError("percentage").getDefaultMessage();
    }

}
