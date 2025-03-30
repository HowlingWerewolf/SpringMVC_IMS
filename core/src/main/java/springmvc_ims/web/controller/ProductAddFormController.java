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

import springmvc_ims.repository.dao.ProductDaoImpl;
import springmvc_ims.repository.model.Product;
import springmvc_ims.service.ProductService;

@Controller
public class ProductAddFormController {

    /** Logger for this class and subclasses */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;
    
    @Autowired
	@Qualifier("productValidator")
	private Validator validator;

    /* TODO: handle with service */
    @Autowired
    private ProductDaoImpl productDao;
    
    @InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

    @PostMapping(value="/productadd") 
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
    
    @GetMapping(value = "/productadd") 
    public String displayLogin(Model model) {     
	   	model.addAttribute("productadd", new Product()); 
        return "productadd"; 
    }

}
