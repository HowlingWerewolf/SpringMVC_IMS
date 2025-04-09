package com.ims.web.controller;

import com.ims.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class InventoryController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/hello")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {

        String now = (new Date()).toString();
        log.info("returning hello view with " + now);

        Map<String, Object> myModel = new HashMap<>();
        myModel.put("now", now);
        
        if (productService.getProducts() != null) {
        	myModel.put("products", productService.getProducts());
        } else {
        	myModel.put("products", new ArrayList<>());
        }
                
        return new ModelAndView("hello", "model", myModel);
    }
    
}
