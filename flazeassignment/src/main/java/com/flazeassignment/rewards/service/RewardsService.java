package com.flazeassignment.rewards.service;

import java.util.List;

import com.flazeassignment.rewards.model.Transaction;

public interface RewardsService {
	public Integer getRewardsForCustomer(String customerId);
	public Integer getRewardsForLastThreeMonths(List<Transaction> transactions);
}
