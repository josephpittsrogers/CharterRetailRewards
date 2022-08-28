package com.charter.retail.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.charter.retail.rewards.service.RewardsService;
import lombok.Data;

/**
* Rewards Controller
* 
* Exposes endpoints that allow viewing of the rewards data for a particular client monthly and total for a 3 month period
* @author JoeRogers
* 
*/
@Data
@RestController
@RequestMapping(path="/rewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

     /*
      *   The purpose of this end point is to display the reward points for a customer for the 
      *   last 3 months
      */
      @GetMapping(path="/getAllRewards/{customerId}", produces = "application/text")
      @ResponseBody
	  public String getAllRewards(@PathVariable("customerId") String customerId) {
     	  String rewards = rewardsService.getRewardsForCustomer(customerId);
		  return rewards;
	  }
       
    	
	/*
	 *   The purpose of this end point is to display the reward points for a customer for the 
	 *   last 3 months
	 */
	  @GetMapping(path="/getRewardsByMonth/{customerId}", produces = "application/text")
	  @ResponseBody
	  public String getRewardsByMonth(@PathVariable("customerId") String customerId) {
		   String rewards = rewardsService.getRewardsForCustomerByMonth(customerId);
		   return rewards;
	}
}