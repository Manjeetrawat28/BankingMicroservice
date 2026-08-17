package com.bank.notification.DTO;

import java.math.BigDecimal;

public class UserData {
	
	private String txnId;
	private BigDecimal amount;
	private String to_account;
	private String from_account;
	
	public UserData(){
		
	}

	public UserData(String txnId, BigDecimal amount, String to_email, String from_email) {
		this.txnId = txnId;
		this.to_account = to_email;
		this.from_account = from_email;
		this.amount = amount;
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

	@Override
	public String toString() {
		return "UserData [TxnId=" + txnId + ", amount=" + amount + ", to_account=" + to_account + ", from_account=" + from_account + "]";
	}
	

}
