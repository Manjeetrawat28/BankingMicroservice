package com.microservice.account.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransferReqDTO {
	
	
	@NotBlank(message = "Txn ID can not be null")
	private String txn_id;
	@NotBlank(message = "From Account can not be null")
	private String from_account;
	@NotBlank(message = "To account can not be null")
	private String to_account;
	@NotNull(message = "Amount can not be null")
	private BigDecimal amount;

	public TransferReqDTO(String txn_id,String from_account, String to_account, BigDecimal amount) {
		super();
		this.txn_id = txn_id;
		this.from_account = from_account;
		this.to_account = to_account;
		this.amount = amount;
	}
	
	public String getTxn_id() {
		return txn_id;
	}

	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "TransferReqDTO [txn_id=" + txn_id + ", from_account=" + from_account + ", to_account=" + to_account
				+ ", amount=" + amount + "]";
	}


	
	
	
	
	
}
