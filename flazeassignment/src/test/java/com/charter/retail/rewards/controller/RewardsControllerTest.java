package com.charter.retail.rewards.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.charter.retail.rewards.model.Transaction;
import com.charter.retail.rewards.repository.RewardsRepository;
import com.charter.retail.rewards.service.RewardsService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SpringBootTest
public class RewardsControllerTest {
 
	private List<Transaction> transactions;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Logger logger;
	
    @InjectMocks
    RewardsController rewardsController;
     
    @Mock
    RewardsService rewardsService;
	     
    @Before
    public void getTransactionList() {
    	transactions = new ArrayList<Transaction>();
    	Transaction transaction = new Transaction();
    	transaction.setId(1);
    	transaction.setCustomerId("cust2");
    	transaction.setTransactionAmount(new BigDecimal(123.45));
    	transaction.setTransactionDate(Calendar.getInstance().getTime());
    	transactions.add(transaction);
        logger = LoggerFactory.getLogger(RewardsControllerTest.class);
        logger.info("@Before: executedBeforeEach to test transaction list");
    }

    

    @Test
    public void testGetAllRewards () 
    {
    	String customerId = "cust2";
    	String month = "August";
    	String serviceRtn =  "{ customer: cust2 rewards: 353.10 }";
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        // returned from service { customer: cust2 rewards: 353.10}
//        Mockito.when(rewardsService.getRewardsForCustomer(customerId).thenReturn(transactions);
		try {
        mockMvc.perform(get("/rewards/getAllRewards/{customerId}", customerId)
        		.contentType("application/text")) .andExpect(status().isOk());
		} catch (Exception e) {
			logger.error("mockMvc Exception: " + e.getMessage());
		}
        String responseEntity = rewardsController.getAllRewards(customerId);
	         int i = 0;
    }

}
 	     
