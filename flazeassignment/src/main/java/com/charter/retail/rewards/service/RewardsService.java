package com.charter.retail.rewards.service;

import java.math.BigDecimal;

import com.charter.retail.rewards.repository.RewardsRepository;

public interface RewardsService {
	public BigDecimal getRewardsForCustomer(String customerId);
	public String getRewardsForCustomerByMonth(String customerId);
	public void setRewardsRepository(RewardsRepository rewardsRepository);
}
