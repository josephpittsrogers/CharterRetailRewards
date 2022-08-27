package com.charter.retail.rewards.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.charter.retail.rewards.model.Transaction;

import java.util.List;

@Repository
public interface RewardsRepository extends CrudRepository<Transaction, Integer> {
   
	public List<Transaction> findByCustomerId(String customerId);

}
