package springmvc_ims.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping(value = "/")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
    	try {
	        String now = (new Date()).toString();
	        logger.info("Returning hello view at " + now);
	
	        return new ModelAndView("hello");
    	} catch (Exception e) {
    		logger.error("Error occured: " + ExceptionUtils.getStackTrace(e));
    	}
		return null;
    }

}