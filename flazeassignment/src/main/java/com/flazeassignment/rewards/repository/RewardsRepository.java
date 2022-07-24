package com.flazeassignment.rewards.repository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.flazeassignment.rewards.model.Transaction;

public interface RewardsRepository extends CrudRepository<Transaction, Integer> {
   
	public List<Transaction> findByCustomerId(String customerId);

}
