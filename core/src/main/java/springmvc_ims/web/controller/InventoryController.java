package springmvc_ims.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import springmvc_ims.service.ProductService;

@Controller
public class InventoryController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/hello")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String now = (new Date()).toString();
        logger.info("returning hello view with " + now);

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        
        if (productService.getProducts() != null) {
        	myModel.put("products", productService.getProducts());
        } else {
        	myModel.put("products", new ArrayList<>());
        }
                
        return new ModelAndView("hello", "model", myModel);
    }
    
}
