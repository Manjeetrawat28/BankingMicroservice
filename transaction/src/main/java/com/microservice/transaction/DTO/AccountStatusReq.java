package com.microservice.transaction.DTO;

public class AccountStatusReq {
	
	private String txnId;
	private String from_account;
	private String to_account;
	public AccountStatusReq(String txnId, String from_account, String to_account) {
		super();
		this.txnId = txnId;
		this.from_account = from_account;
		this.to_account = to_account;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
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
	
	
	
}
