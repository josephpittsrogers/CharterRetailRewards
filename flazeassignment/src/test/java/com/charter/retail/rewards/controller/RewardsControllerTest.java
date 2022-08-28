package com.charter.retail.rewards.controller;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.charter.retail.rewards.model.Transaction;
import com.charter.retail.rewards.service.RewardsService;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(RewardsController.class)
public class RewardsControllerTest {
 
	  public static List<Transaction> cust1Transactions = new ArrayList<Transaction>();
	  public static List<Transaction> cust2Transactions = new ArrayList<Transaction>();
	  public static List<Transaction> cust3Transactions = new ArrayList<Transaction>();	  
	  public static Logger logger;	
		
	  @Autowired
	  private MockMvc mockMvc;
		
	  @Autowired
	  private RewardsController rewardsController;
	     
	  @MockBean
	  private RewardsService mockRewardsService;
	      

	    @Test
	    public void testGetAllRewards () 
	    {
	    	String customerId = "cust1";
			BigDecimal threeMonthsRewards = new BigDecimal(80.45);
			String rewards = "{ customer: " + customerId + " rewards: " + threeMonthsRewards + " }";
			when(mockRewardsService.getRewardsForCustomer(customerId)).thenReturn(rewards);
			try {
	        mockMvc.perform(get("/rewards/getAllRewards/{customerId}", customerId)
	        		.contentType("application/text")) .andExpect(status().isOk());
			} catch (Exception e) {
				logger.error("mockMvc Exception: " + e.getMessage());
			}
	        String response = rewardsController.getAllRewards(customerId);
	        assertSame(rewards, response);
	    }

	    @Test
	    public void testGetRewardsByMonth () 
	    {
	    	String customerId = "cust2";
			String rewards = "{ { customer: cust2 month: June rewards:310.22 }{ customer: cust2 month: July rewards:1.45 }{ customer: cust2 month: August rewards:41.43 } }";
			when(mockRewardsService.getRewardsForCustomerByMonth(customerId)).thenReturn(rewards);
			try {
	        mockMvc.perform(get("/rewards/getRewardsByMonth/{customerId}", customerId)
	        		.contentType("application/text")) .andExpect(status().isOk());
			} catch (Exception e) {
				logger.error("mockMvc Exception: " + e.getMessage());
			}
	        String response = rewardsController.getRewardsByMonth(customerId);
	        assertSame(rewards, response);
	    }

}
 	     
