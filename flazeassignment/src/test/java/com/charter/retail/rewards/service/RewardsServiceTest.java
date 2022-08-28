package com.charter.retail.rewards.service;

import static org.junit.Assert.assertTrue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.charter.retail.rewards.model.Transaction;
import com.charter.retail.rewards.repository.RewardsRepository;
import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;

@RunWith(MockitoJUnitRunner.class)
public class RewardsServiceTest {
	
	  private RewardsService rewardsService = new RewardsServiceImpl();	  
	  public static List<Transaction> cust1Transactions = new ArrayList<Transaction>();
	  public static List<Transaction> cust2Transactions = new ArrayList<Transaction>();
	  public static List<Transaction> cust3Transactions = new ArrayList<Transaction>();	  
	  public static Logger logger;	
	 	     
	  @Mock
	  @Autowired
	  RewardsRepository mockRewardsRepository;
	     
	  @BeforeClass
	  public static void getTransactionList() {
	 	 Transaction transaction = new Transaction();
	 	 transaction.setId(1);
	 	 transaction.setCustomerId("cust1");
	 	 transaction.setTransactionAmount(new BigDecimal(120.00));
	 	 transaction.setTransactionDate(Calendar.getInstance().getTime());
	 	 cust1Transactions.add(transaction);
	 	 transaction = new Transaction();
	 	 transaction.setId(2);
	 	 transaction.setCustomerId("cust2");
	 	 transaction.setTransactionAmount(new BigDecimal(90.00));
	 	 transaction.setTransactionDate(Calendar.getInstance().getTime());
	 	 cust2Transactions.add(transaction);
	 	 transaction = new Transaction();
	 	 transaction.setId(3);
	 	 transaction.setCustomerId("cust3");
	 	 transaction.setTransactionAmount(new BigDecimal(40.00));
	 	 transaction.setTransactionDate(Calendar.getInstance().getTime());
	 	 cust3Transactions.add(transaction);
	     logger = LoggerFactory.getLogger(RewardsServiceTest.class);
	     logger.info("@BeforeClass: executedBeforeClass to test rewards scenarios");
	  }

	  @Test public void testAbove100() {  
		  String customerId = cust1Transactions.get(0).getCustomerId();
		  when(mockRewardsRepository.findByCustomerId(customerId)).thenReturn(cust1Transactions);
		  rewardsService.setRewardsRepository(mockRewardsRepository);
		  String rewards = rewardsService.getRewardsForCustomer(customerId);
		  assertTrue(rewards.contains("rewards: 90"));
	  }
	  
	  @Test public void testAbove50() {
		  String customerId = cust2Transactions.get(0).getCustomerId();
		  when(mockRewardsRepository.findByCustomerId(customerId)).thenReturn(cust2Transactions);
		  rewardsService.setRewardsRepository(mockRewardsRepository);
		  String rewards = rewardsService.getRewardsForCustomer(customerId);
		  assertTrue(rewards.contains("rewards: 40"));
	  }
	  
	  @Test public void testBelow50() {
		  String customerId = cust3Transactions.get(0).getCustomerId();
		  when(mockRewardsRepository.findByCustomerId(customerId)).thenReturn(cust3Transactions);
		  rewardsService.setRewardsRepository(mockRewardsRepository);
		  String rewards = rewardsService.getRewardsForCustomer(customerId);
		  assertTrue(rewards.contains("rewards: 0"));
	  }
	 
}
