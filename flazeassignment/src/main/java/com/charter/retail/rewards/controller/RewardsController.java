package com.charter.retail.rewards.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.charter.retail.rewards.service.RewardsService;

import org.apache.log4j.Logger;

@RestController
@RequestMapping(path="/rewards")
public class RewardsController {
	
	public static String INTERNAL_SERVER_ERROR = "Internal Server Error";
	
	private static final Logger logger = Logger.getLogger(RewardsController.class);

    @Autowired
    private RewardsService rewardsService;
    
    /*
     *   The purpose of this end point is to display the reward points for a customer for the 
     *   last 3 months
     */
       @GetMapping(path="/getAllRewards/{customerId}", produces = "text/html")
       @ResponseBody
    	  public String getAllRewards(@PathVariable("customerId") String customerId) {
    		  BigDecimal rewards = rewardsService.getRewardsForCustomer(customerId);
    		  return "{ customer: " + customerId + " rewards: " + rewards + "}";
    	  }
       
    	 @ExceptionHandler(Exception.class)
    	 public ModelAndView handleException(Exception ex) {
    		 logger.error("Controller Error" + ex.getMessage());
    	     ModelAndView model = new ModelAndView(INTERNAL_SERVER_ERROR);
    	  
    	     model.addObject("Exception", ex.getMessage());
    	  
    	     return model;
    	 }
 
/*
 *   The purpose of this end point is to display the reward points for a customer for the 
 *   last 3 months
 */
   @GetMapping(path="/getRewardsByMonth/{customerId}", produces = "text/html")
   @ResponseBody
	  public String getRewardsByMonth(@PathVariable("customerId") String customerId) {
		  String rewards = rewardsService.getRewardsForCustomerByMonth(customerId);
		  return rewards;
	  }
   
	 @ExceptionHandler(Exception.class)
	 public ModelAndView handleIOException(Exception ex) {
		 logger.error("Controller Error" + ex.getMessage());
	     ModelAndView model = new ModelAndView("Controller Error");
	  
	     model.addObject("exception", ex.getMessage());
	  
	     return model;
	 }
	 
}