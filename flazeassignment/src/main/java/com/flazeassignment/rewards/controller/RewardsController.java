package com.flazeassignment.rewards.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.flazeassignment.rewards.service.RewardsService;

import org.apache.log4j.Logger;

@RestController
@RequestMapping(path="/rewards")
public class RewardsController {
	
	private static final Logger logger = Logger.getLogger(RewardsController.class);

    @Autowired
    private RewardsService rewardsService;
    
/*
 *   The purpose of this end point is to display the reward points for a customer for the 
 *   last 3 months
 */
   @GetMapping(path="/getRewards/{customerId}", produces = "text/html")
   @ResponseBody
	  public String getRewards(@PathVariable("customerId") String customerId) {
		  Integer rewards = rewardsService.getRewardsForCustomer(customerId);
		  return "The number of rewards for customer: " + customerId + " is " + rewards;
	  }
   
 @ExceptionHandler(IOException.class)
 public ModelAndView handleIOException(IOException ex) {
	 logger.error("Controller IOError" + ex.getMessage());
     ModelAndView model = new ModelAndView("Controller IOError");
  
     model.addObject("exception", ex.getMessage());
  
     return model;
 }
}