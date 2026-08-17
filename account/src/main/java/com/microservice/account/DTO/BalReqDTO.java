package com.microservice.account.DTO;

public class BalReqDTO {
	
	String txnId;
	Integer user_id;
	String account_number;
	public BalReqDTO(String txnId, Integer user_id, String account_number) {
		
		super();
		this.txnId = txnId;
		this.user_id = user_id;
		this.account_number = account_number;
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
	
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	@Override
	public String toString() {
		return "BalReqDTO [txnId=" + txnId + ", user_id=" + user_id + ", account_number=" + account_number + "]";
	}
	
	

}
