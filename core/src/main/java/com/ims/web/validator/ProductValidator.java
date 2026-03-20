package com.ims.web.validator;

import com.ims.repository.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@Slf4j
public class ProductValidator implements Validator {

	private final int DEFAULT_MIN_PRICE = 0;

    @SuppressWarnings("rawtypes")
	public boolean supports(final Class clazz) {
        return Product.class.equals(clazz);
    }

    public void validate(final Object obj, final Errors errors) {
    	log.info("Validation started!");
        final Product product = (Product) obj;
        
        try {
            int minPrice = DEFAULT_MIN_PRICE;
            if (product.getDescription().isEmpty()) {
	            errors.rejectValue("description", "error.not-specified", null, "Value required.");
	        } else if (product.getPrice() < minPrice) {
		        errors.rejectValue("price", "error.too-low", 
		        	new Object[] {minPrice}, "Value too low.");
	        }
    	} catch (final NullPointerException ex) {
            errors.rejectValue("price", "error.not-specified", null, "Value required.");  
    	}
    }

}
