package com.usermicroservice.user.Entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "account_master")
public class AccountMasterEntity {
	
	@Id
	@Column(name = "account_number")
	private String account_number;
	
	@Column(name = "user_id")
	private Integer user_id;
	
	@Column(name = "amount")
	private BigDecimal 	 amount;
	
	@Column(name = "Status")
	private String status;
	
	private AccountMasterEntity() {
		
	}

	public AccountMasterEntity(String account_number, Integer user_id, BigDecimal amount, String status) {
		super();
		this.account_number = account_number;
		this.user_id = user_id;
		this.amount = amount;
		this.status = status;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AccountMasterEntity [account_number=" + account_number + ", user_id=" + user_id + ", amount=" + amount
				+ ", status=" + status + "]";
	}
	
	
	
}
