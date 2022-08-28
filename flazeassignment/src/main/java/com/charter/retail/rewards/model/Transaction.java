package com.charter.retail.rewards.model;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

/**
* Transaction Model 
* 
* Data model for the transaction data set up to be used by JPA
* These data have the transactions used to calculate the rewards based on the transaction amount and date
* @author JoeRogers
* 
*/
@Entity
@Table(name = "transaction")
@Data
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "customer_id")
	private String customerId;
	@Column(name = "transaction_amount")
	private BigDecimal transactionAmount;
	@Column(name = "transaction_date")
	private Date transactionDate;
}
