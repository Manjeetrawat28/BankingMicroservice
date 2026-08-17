package com.microservice.transaction.DTO;

public class BalEnqReq {
	private String txnId;
	private String account_number;

	public BalEnqReq(String txnId, String account_number) {
		super();
		this.txnId = txnId;
		this.account_number = account_number;
	}

	public String getaccount_number() {
		return account_number;
	}

	public void setaccount_number(String from_account) {
		this.account_number = from_account;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	
	
	
}
