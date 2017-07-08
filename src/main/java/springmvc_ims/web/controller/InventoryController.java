package springmvc_ims.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import springmvc_ims.service.ProductService;

@Controller
public class InventoryController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String now = (new java.util.Date()).toString();
        logger.info("returning hello view with " + now);

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        myModel.put("products", this.productService.getProducts());

        return new ModelAndView("hello", "model", myModel);
    }

    protected ProductService getProductService() {
		return productService;
	}

	protected void setProductService(ProductService productService) {
        this.productService = productService;
    }
    
}
