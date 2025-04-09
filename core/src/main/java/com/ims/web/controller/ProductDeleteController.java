package com.ims.web.controller;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import com.ims.service.ProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class ProductDeleteController {

    @Autowired
    private ProductService productService;

    /* TODO: handle with service */
    @Autowired
    private ProductDaoImpl productDao;

    @RequestMapping(method = RequestMethod.POST, value="/productdelete") 
    public ModelAndView onSubmit(@ModelAttribute("productdelete") Product command, BindingResult result)
            throws ServletException {
        
        if(result.hasErrors()) {
            log.info("I know something is not ok. Errors below:");
            for (ObjectError error : result.getAllErrors()) {
                log.info(error.getDefaultMessage());
            }            
            return null;
        }

        int id = ((Product) command).getId();
        String description = ((Product) command).getDescription();
        Double price = ((Product) command).getPrice();
        
        log.info("deleting from DB this product: " + description + " with price " + price + " with ID " + id);
        
        productDao.delete(command);

        log.info("returning from PriceIncreaseForm");
        
        return new ModelAndView(new RedirectView("hello"));
    }

    protected Object formBackingObject(HttpServletRequest request) throws ServletException {
        Product product = new Product();
        product.setDescription("dummy");
        product.setPrice(-1.0d);
        log.info("productdelete object set with " + product.getDescription() + " with price " + product.getPrice());
        return product;
    }
    
    @GetMapping(value = "/productdelete") 
    public ModelAndView displayLogin(Model model) {     	
	   
	   	model.addAttribute("productdelete", new Product()); 
        String now = (new java.util.Date()).toString();
        log.info("returning productdelete view with " + now);

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        myModel.put("products", this.productService.getProducts());

        return new ModelAndView("productdelete", "model", myModel);
    }

}
