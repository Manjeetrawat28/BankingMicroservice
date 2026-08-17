package com.microservice.account.DTO;

import jakarta.validation.constraints.NotBlank;

public class Accounts {
	
	
	@NotBlank(message = "TnxId can not be null")
	String txnId;
	@NotBlank(message = "From Account can not be NULL")
	String from_account;
	@NotBlank(message = "To Account can not be NULL")
	String to_account;
	public Accounts(String TxnId,String from_account, String to_account) {
		super();
		this.txnId = txnId;
		this.from_account = from_account;
		this.to_account = to_account;
	}
	public String getFrom_account() {
		return from_account;
	}
	public void setFrom_account(String from_account) {
		this.from_account = from_account;
	}
	public String getTo_account() {
		return to_account;
	}
	public void setTo_account(String to_account) {
		this.to_account = to_account;
	}
	
	
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	@Override
	public String toString() {
		return "Accounts [txnId=" + txnId + ", from_account=" + from_account + ", to_account=" + to_account + "]";
	}
	
	
}
