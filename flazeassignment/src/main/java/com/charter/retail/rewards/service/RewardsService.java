package com.charter.retail.rewards.service;

import java.math.BigDecimal;

public interface RewardsService {
	public BigDecimal getRewardsForCustomer(String customerId);
	public String getRewardsForCustomerByMonth(String customerId);
}
