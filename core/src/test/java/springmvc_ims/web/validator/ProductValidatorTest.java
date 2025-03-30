package springmvc_ims.web.validator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import junit.framework.TestCase;
import springmvc_ims.repository.model.Product;

public class ProductValidatorTest extends TestCase {
	
	@InjectMocks
	private ProductValidator productValidator;
	
	private Product product;
	private Errors errors;
    
	@Before
    public void setUp() throws Exception {
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
    public void testValidateNoPrice() {
    	product.setId(1);
    	product.setDescription("aaa");
    	product.setPrice(null);
    	errors = new BeanPropertyBindingResult(product, "price");
    	productValidator.validate(product, errors);
    	assertEquals("Value required.", getPriceErrorMessage()); 
    }

	@Test
    public void testValidateTooLowPrice() {
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
    	} catch (NullPointerException ex) {}
    	
    	return "";
    }

}
