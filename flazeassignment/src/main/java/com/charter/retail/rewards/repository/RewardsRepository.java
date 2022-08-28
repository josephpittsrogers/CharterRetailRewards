package com.charter.retail.rewards.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.charter.retail.rewards.model.Transaction;

import java.util.List;

/**
* Rewards Repository
* 
* Extends the Spring CrudRepository that only requires interfaces describe CRUD and specific customized inquiries.  Spring actually 
* creates the implementations.
* @author JoeRogers
* 
*/
@Repository
public interface RewardsRepository extends CrudRepository<Transaction, Integer> {
   
	public List<Transaction> findByCustomerId(String customerId);

}
