package com.microservice.transaction.DTO;

import java.math.BigDecimal;

public class NotificationDTO {

	private String txnId;
	private BigDecimal amount;
	private String to_account;
	private String from_account;
	public NotificationDTO(String txnId, BigDecimal amount, String to_account,
			String from_account) {
		super();
		this.txnId = txnId;
		this.amount = amount;
		this.to_account = to_account;
		this.from_account = from_account;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getTo_account() {
		return to_account;
	}
	public void setTo_account(String to_account) {
		this.to_account = to_account;
	}
	public String getFrom_account() {
		return from_account;
	}
	public void setFrom_account(String from_account) {
		this.from_account = from_account;
	}
	
	
		
}
