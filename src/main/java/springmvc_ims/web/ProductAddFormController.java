package springmvc_ims.web;

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
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import springmvc_ims.dao.ProductDao;
import springmvc_ims.model.Product;
import springmvc_ims.web.service.ProductManager;

@Controller
public class ProductAddFormController {

    /** Logger for this class and subclasses */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductManager productManager;
    
    @Autowired
	@Qualifier("productValidator")
	private Validator validator;

    @Autowired
    private ProductDao productDao;
    
    @InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

    @RequestMapping(method = RequestMethod.POST) 
    public ModelAndView onSubmit(@ModelAttribute("productadd") @Valid Product command, BindingResult result)
            throws ServletException {
        
        if(result.hasErrors()) {
            logger.info("I know something is not ok with the PI. Errors below:");
            for (ObjectError error : result.getAllErrors()) {
                logger.info(error.getDefaultMessage());           	
            }            
            return null;
        }

        String description = ((Product) command).getDescription();
        Double price = ((Product) command).getPrice();
        
        logger.info("adding to DB this product: " + description + " with price " + price);        
        productDao.save(command);
        logger.info("returning from ProductDeleteController");	
        
        return new ModelAndView(new RedirectView("hello"));
    }

    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        Product product = new Product();
        product.setDescription("dummy");
        product.setPrice(1.0d);
        logger.info("productadd object set with " + product.getDescription() + " with price " + product.getPrice());
        return product;
    }
    
    @RequestMapping(value = "/productadd", method = RequestMethod.GET) 
    public String displayLogin(Model model) {     
	   	model.addAttribute("productadd", new Product()); 
        return "productadd"; 
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

}
