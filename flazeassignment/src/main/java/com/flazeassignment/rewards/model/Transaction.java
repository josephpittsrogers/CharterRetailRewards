package com.flazeassignment.rewards.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

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
