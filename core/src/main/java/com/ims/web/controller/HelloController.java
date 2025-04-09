package com.ims.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@Slf4j
public class HelloController {

    @GetMapping(value = "/")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
    	try {
	        String now = (new Date()).toString();
	        log.info("Returning hello view at " + now);
	
	        return new ModelAndView("hello");
    	} catch (Exception e) {
    		log.error("Error occured: " + ExceptionUtils.getStackTrace(e));
    	}
		return null;
    }

}