package springmvc_ims.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import springmvc_ims.dao.ProductDao;
import springmvc_ims.model.Product;
import springmvc_ims.web.service.ProductManager;

@Controller
public class ProductDeleteController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ProductManager productManager;

    @Autowired
    private ProductDao productDao;

    @RequestMapping(method = RequestMethod.POST) 
    public ModelAndView onSubmit(@ModelAttribute("productdelete") Product command, BindingResult result)
            throws ServletException {
        
        if(result.hasErrors()) {
            logger.info("I know something is not ok. Errors below:");
            for (ObjectError error : result.getAllErrors()) {
                logger.info(error.getDefaultMessage());           	
            }            
            return null;
        }

        int id = ((Product) command).getId();
        String description = ((Product) command).getDescription();
        Double price = ((Product) command).getPrice();
        
        logger.info("deleting from DB this product: " + description + " with price " + price + " with ID " + id);
        
        productDao.delete(command);

        logger.info("returning from PriceIncreaseForm");	
        
        return new ModelAndView(new RedirectView("hello"));
    }

    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        Product product = new Product();
        product.setDescription("dummy");
        product.setPrice(-1.0d);
        logger.info("productdelete object set with " + product.getDescription() + " with price " + product.getPrice());
        return product;
    }
    
    @RequestMapping(value = "/productdelete", method = RequestMethod.GET) 
    public ModelAndView displayLogin(Model model) {     	
	   
	   	model.addAttribute("productdelete", new Product()); 
        String now = (new java.util.Date()).toString();
        logger.info("returning productdelete view with " + now);

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        myModel.put("products", this.productManager.getProducts());

        return new ModelAndView("productdelete", "model", myModel);
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public ProductManager getProductManager() {
        return productManager;
    }

}
