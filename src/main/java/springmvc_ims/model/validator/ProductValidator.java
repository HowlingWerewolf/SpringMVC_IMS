package springmvc_ims.model.validator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import springmvc_ims.model.Product;

public class ProductValidator implements Validator {

	private int DEFAULT_MIN_PRICE = 0;
    private int minPrice = DEFAULT_MIN_PRICE;
    
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean supports(Class clazz) {
        return Product.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
    	logger.info("Validation started!");
        Product product = (Product) obj;
        
        try {
	        if (product.getDescription().equals("")) {
	            errors.rejectValue("description", "error.not-specified", null, "Value required.");
	        }
	        else if (product.getPrice() < minPrice) {
		        errors.rejectValue("price", "error.too-low", 
		        	new Object[] {new Integer(minPrice)}, "Value too low.");
	        }
    	} catch (NullPointerException ex) {
            errors.rejectValue("price", "error.not-specified", null, "Value required.");  
    	}
    }

}