package springmvc_ims.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import springmvc_ims.service.PriceIncrease;
import springmvc_ims.service.ProductManager;

@Controller
public class PriceIncreaseFormController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ProductManager productManager;
    
    @RequestMapping(method = RequestMethod.POST) 
    public ModelAndView onSubmit(@ModelAttribute("priceincrease") PriceIncrease command)
            throws ServletException {

        logger.info("Command received: " + command);
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
