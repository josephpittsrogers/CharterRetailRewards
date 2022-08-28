package com.charter.retail.rewards.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
* Controller Exception Handler 
* 
* Uses AOP to implement Exception Handling for the endpoints of the controller
* @author JoeRogers
* 
*/
@ControllerAdvice
public class ControllerExceptionHandler {	
	 public static String INTERNAL_SERVER_ERROR = "Internal Server Error";	
     Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	   
	 @ExceptionHandler(Exception.class)
	 public ModelAndView handleException(Exception ex) {
		 logger.error("Controller Error" + ex.getMessage());
	     ModelAndView model = new ModelAndView("Controller Error");
	  
	     model.addObject("exception", ex.getMessage());
	  
	     return model;
	 }

}
