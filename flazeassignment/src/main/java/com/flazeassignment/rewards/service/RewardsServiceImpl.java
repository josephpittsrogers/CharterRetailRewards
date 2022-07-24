package com.flazeassignment.rewards.service;

import java.util.List;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flazeassignment.rewards.model.Transaction;
import com.flazeassignment.rewards.repository.RewardsRepository;

import org.apache.log4j.Logger;

@Service
public class RewardsServiceImpl implements RewardsService {
	
	private static final Logger logger = Logger.getLogger(RewardsServiceImpl.class);
	
	public static final String EMPTY_STRING = "";
	
	@Autowired
	private RewardsRepository rewardsRepository;

	public Integer getRewardsForCustomer(String customerId) {
		List<Transaction> transactions = rewardsRepository.findByCustomerId(customerId);
		if (transactions == null) {
			logger.error("Customer Id: " + customerId + " not retrieved or no transactions");
			return null;
		}
		Integer threeMonthsRewards = getRewardsForLastThreeMonths(transactions);
		
		return threeMonthsRewards;
	}
	
	private Integer getRewardsForLastThreeMonths(List<Transaction> transactions) {
		Integer rewards = 0;
		Date threeMonthsBefore = getThreeMonthsBefore();
		BigDecimal lowerLimit = new BigDecimal(50.00);
		for (Transaction t : transactions) {
			if (t.getTransactionDate().before(threeMonthsBefore)) {
				continue;
			}
			if (t.getTransactionAmount().compareTo(lowerLimit) < 1) {
				continue;
			}
			rewards = rewards + calculateRewards(t.getTransactionAmount());
		}

		return rewards;
	}
	
	private Integer calculateRewards(BigDecimal transactionAmount) {
		Integer rewards = 0;
		Integer transInt = transactionAmount.intValue();
		BigDecimal hundredLimit = new BigDecimal(100.00);
		if (transactionAmount.compareTo(hundredLimit) > 0) {

			rewards = 50 + (2 * (transInt - 100));
		}
		else {
			rewards = transInt - 50;
		}
		return rewards;
	}
	
	private Date getThreeMonthsBefore() {
		Date referenceDate = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(referenceDate); 
		c.add(Calendar.MONTH, -3);
		return c.getTime();
	}
	
}
