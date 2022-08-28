package com.charter.retail.rewards.service;

import com.charter.retail.rewards.repository.RewardsRepository;

public interface RewardsService {
	public String getRewardsForCustomer(String customerId);
	public String getRewardsForCustomerByMonth(String customerId);
	public void setRewardsRepository(RewardsRepository rewardsRepository);
}
