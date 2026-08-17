package com.microservice.account.DTO;

import java.math.BigDecimal;

public class BalEnqResp {
	String txnId;
	Integer user_id;
	String account_number;
	BigDecimal amount;
	public BalEnqResp(String txnId,Integer user_id, String account_number, BigDecimal amount) {
		super();
		this.txnId = txnId;
		this.user_id = user_id;
		this.account_number = account_number;
		this.amount = amount;
	}
	public BalEnqResp(Integer user_id, String account_number, BigDecimal amount) {
		super();
		this.user_id = user_id;
		this.account_number = account_number;
		this.amount = amount;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	@Override
	public String toString() {
		return "BalEnqResp [txnId=" + txnId + ", user_id=" + user_id + ", account_number=" + account_number
				+ ", amount=" + amount + "]";
	}
	
	
}
