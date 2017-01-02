package springmvc_ims.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import springmvc_ims.web.form.PriceIncrease;
import springmvc_ims.web.service.ProductManager;

@Controller
public class PriceIncreaseFormController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ProductManager productManager;
    
    @Autowired
	@Qualifier("priceIncreaseValidator")
	private Validator validator;
    
    @InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

    @RequestMapping(method = RequestMethod.POST) 
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
        productManager.increasePrice(increase);

        logger.info("returning from PriceIncreaseForm");	
        
        return new ModelAndView(new RedirectView("hello"));
    }

    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        PriceIncrease priceIncrease = new PriceIncrease();
        priceIncrease.setPercentage(20);
        logger.info("priceincrease object set with " + priceIncrease.getPercentage() + "%");
        return priceIncrease;
    }
    
    @RequestMapping(value = "/priceincrease", method = RequestMethod.GET) 
    public String displayLogin(Model model) {     	
	   
	   	// very special thanks to this solution!!!  http://stackoverflow.com/questions/8781558/neither-bindingresult-nor-plain-target-object-for-bean-name-available-as-request
        model.addAttribute("priceincrease", new PriceIncrease()); 
        return "priceincrease"; 
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

}
