package com.charter.retail.rewards.repository;
import org.springframework.data.repository.CrudRepository;

import com.charter.retail.rewards.model.Transaction;

import java.util.List;

public interface RewardsRepository extends CrudRepository<Transaction, Integer> {
   
	public List<Transaction> findByCustomerId(String customerId);

}
