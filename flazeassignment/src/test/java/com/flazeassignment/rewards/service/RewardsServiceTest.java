package com.flazeassignment.rewards.service;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.flazeassignment.rewards.model.Transaction;
import org.junit.Test;

public class RewardsServiceTest {
	
	private RewardsService rewardsService = new RewardsServiceImpl();

	@Test
	public void testAbove100() {

		Transaction transaction = new Transaction();
		transaction.setId(1);
		transaction.setCustomerId("cust1");
		transaction.setTransactionAmount(new BigDecimal(120.00));
		transaction.setTransactionDate(Calendar.getInstance().getTime());	
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		Integer rewards = rewardsService.getRewardsForLastThreeMonths(transactions);
		assertEquals(90, rewards.intValue());
	}
	
	@Test
	public void testAbove50() {

		Transaction transaction = new Transaction();
		transaction.setId(1);
		transaction.setCustomerId("cust1");
		transaction.setTransactionAmount(new BigDecimal(90.00));
		transaction.setTransactionDate(Calendar.getInstance().getTime());	
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		Integer rewards = rewardsService.getRewardsForLastThreeMonths(transactions);
		assertEquals(40, rewards.intValue());
	}
	
	@Test
	public void testBelow50() {

		Transaction transaction = new Transaction();
		transaction.setId(1);
		transaction.setCustomerId("cust1");
		transaction.setTransactionAmount(new BigDecimal(40.00));
		transaction.setTransactionDate(Calendar.getInstance().getTime());	
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);
		Integer rewards = rewardsService.getRewardsForLastThreeMonths(transactions);
		assertEquals(0, rewards.intValue());
	}
}
