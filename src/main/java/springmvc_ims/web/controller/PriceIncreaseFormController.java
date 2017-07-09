package springmvc_ims.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import springmvc_ims.service.ProductService;
import springmvc_ims.web.form.PriceIncrease;

@Controller
public class PriceIncreaseFormController {

    /** Logger for this class and subclasses */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;
    
    @Autowired
	@Qualifier("priceIncreaseValidator")
	private Validator validator;
    
    @InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

    @PostMapping(value="/priceincrease/increase") 
    public ModelAndView onSubmit(@ModelAttribute("priceincrease") @Valid PriceIncrease command, BindingResult result)
            throws ServletException {
        
        if(result.hasErrors()) {
            logger.info("I know something is not ok with the PI. Errors below:");
            for (ObjectError error : result.getAllErrors()) {
                logger.info(error.getDefaultMessage());           	
            }
            return null;
        }

        int increase = ((PriceIncrease) command).getPercentage();
        logger.info("Increasing prices by " + increase + "%.");
        productService.increasePrice(increase);

        logger.info("returning from PriceIncreaseForm");	
        
        return new ModelAndView(new RedirectView("hello"));
    }

    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        PriceIncrease priceIncrease = new PriceIncrease();
        priceIncrease.setPercentage(20);
        logger.info("priceincrease object set with " + priceIncrease.getPercentage() + "%");
        return priceIncrease;
    }
    
    @GetMapping(value = "/priceincrease") 
    public String displayLogin(Model model) {     	
	   
	   	// very special thanks for this solution!!!  http://stackoverflow.com/questions/8781558/neither-bindingresult-nor-plain-target-object-for-bean-name-available-as-request
        model.addAttribute("priceincrease", new PriceIncrease()); 
        return "priceincrease"; 
    }

    protected void setProductService(ProductService productService) {
        this.productService = productService;
    }

    protected ProductService getProductService() {
        return productService;
    }

}
