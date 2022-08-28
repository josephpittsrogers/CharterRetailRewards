package com.charter.retail.rewards.service;

import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.charter.retail.rewards.model.Transaction;
import com.charter.retail.rewards.repository.RewardsRepository;
import lombok.Setter;
import org.slf4j.Logger;

/**
* Rewards Service Implementation
* 
* Implements the Rewards Service Interface.  It performs the business activities required to generate the customer monthly and total rewards
* for the last 3 months.
* @author JoeRogers
* 
*/
@Setter
@Service
public class RewardsServiceImpl implements RewardsService {
	
    Logger logger = org.slf4j.LoggerFactory.getLogger(RewardsServiceImpl.class);
	
	public static final String EMPTY_STRING = "";
	
	@Autowired
	private RewardsRepository rewardsRepository;

    /*
     *   The purpose of this method is to return the total reward points for a customer for the 
     *   last 3 months.
     */
	public String getRewardsForCustomer(String customerId) {
		List<Transaction> transactions = rewardsRepository.findByCustomerId(customerId);
		if (transactions == null) {
			String errorMessage = "Customer Id: " + customerId + " not retrieved or no transactions";
			logger.error(errorMessage);
			return null;
		}
		BigDecimal threeMonthsRewards = getRewardsForLastThreeMonths(transactions);
		String rewards = "{ customer: " + customerId + " rewards: " + threeMonthsRewards + " }";
		return rewards;
	}

    /*
     *   The purpose of this method is to return the monthly breakdown of reward points for a customer for the 
     *   last 3 months.
     */
	public String getRewardsForCustomerByMonth(String customerId) {
		List<Transaction> transactions = rewardsRepository.findByCustomerId(customerId);
		if (transactions == null) {
			String errorMessage = "Customer Id: " + customerId + " not retrieved or no transactions";
			logger.error(errorMessage);
			return null;
		}
		Map<String, BigDecimal > threeMonthsRewards = getRewardsForLastThreeMonthsByMonth(transactions);
		StringBuffer rtn = new StringBuffer("{ ");
		threeMonthsRewards.forEach((key, value) -> rtn.append("{ customer: " + customerId + "   month: " + key + "   rewards:" + value + " }"));
		rtn.append(" }");
		return rtn.toString();
	}
	
	private BigDecimal getRewardsForLastThreeMonths(List<Transaction> transactions) {
		BigDecimal rewards = new BigDecimal(0.00);
		Date threeMonthsBefore = getThreeMonthsBefore();
		BigDecimal lowerLimit = new BigDecimal(50.00);
		for (Transaction t : transactions) {
			if (t.getTransactionDate().before(threeMonthsBefore)) {
				continue;
			}
			if (t.getTransactionAmount().compareTo(lowerLimit) < 1) {
				continue;
			}
			rewards = rewards.add(calculateRewards(t.getTransactionAmount()));
		}

		return rewards;
	}
	
	private Map<String, BigDecimal> getRewardsForLastThreeMonthsByMonth(List<Transaction> transactions) {
		Date threeMonthsBefore = getThreeMonthsBefore();
		BigDecimal lowerLimit = new BigDecimal(50.00);
		HashMap<String, BigDecimal> map = new HashMap<>();
		Calendar c = new GregorianCalendar();
		for (Transaction t : transactions) {;
			if (t.getTransactionDate().before(threeMonthsBefore)) {
				continue;
			}
			if (t.getTransactionAmount().compareTo(lowerLimit) < 1) {
				continue;
			}
			c.setTime(t.getTransactionDate());
			BigDecimal runningTotalRewards = new BigDecimal(0.00);
			BigDecimal newRewards = calculateRewards(t.getTransactionAmount());
			if (map.containsKey(getMonthLiteral(c.get(Calendar.MONTH)))) {
				BigDecimal existingRewards = map.get(getMonthLiteral(c.get(Calendar.MONTH)));
				runningTotalRewards = existingRewards.add(newRewards);
			} else {
				runningTotalRewards = newRewards;
			}
			map.put(getMonthLiteral(c.get(Calendar.MONTH)), runningTotalRewards);
		}

		return map;
	}
	
	private BigDecimal calculateRewards(BigDecimal transactionAmount) {
		BigDecimal rewards = new BigDecimal(0.00);
		BigDecimal transAmt = transactionAmount;
		BigDecimal hundredLimit = new BigDecimal(100.00);
		BigDecimal fiftyLimit = new BigDecimal(50.00);
		BigDecimal hundredFactor = new BigDecimal(2.00);
		if (transactionAmount.compareTo(hundredLimit) > 0) {

			rewards = fiftyLimit.add((transAmt.subtract(hundredLimit)).multiply(hundredFactor));
		}
		else  {  // the transactions <= 50.00 have already been bypasses
			rewards = transAmt.subtract(fiftyLimit);
		}
		return rewards;
	}
	
	private String getMonthLiteral(int month) {
		String monthLiteral = "";
		switch (month) {
		case 0: 
			monthLiteral = "January";
			break;
		case 1: 
			monthLiteral = "February";
			break;
		case 2: 
			monthLiteral = "March";
			break;
		case 3: 
			monthLiteral = "April";
			break;
		case 4: 
			monthLiteral = "May";
			break;
		case 5: 
			monthLiteral = "June";
			break;
		case 6: 
			monthLiteral = "July";
			break;
		case 7: 
			monthLiteral = "August";
			break;
		case 8: 
			monthLiteral = "September";
			break;
		case 9: 
			monthLiteral = "October";
			break;
		case 10: 
			monthLiteral = "November";
			break;
		case 11: 
			monthLiteral = "December";
			break;
		default: 
			monthLiteral = "Unknown";
		}
		return monthLiteral;
	}
	
	private Date getThreeMonthsBefore() {
		Date referenceDate = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(referenceDate); 
		c.add(Calendar.MONTH, -3);
		return c.getTime();
	}
	
}
