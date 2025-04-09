package com.ims.web.validator;

import com.ims.repository.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class ProductValidator implements Validator {

	private int DEFAULT_MIN_PRICE = 0;
    private int minPrice = DEFAULT_MIN_PRICE;
    
    @SuppressWarnings("rawtypes")
	public boolean supports(Class clazz) {
        return Product.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
    	log.info("Validation started!");
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
