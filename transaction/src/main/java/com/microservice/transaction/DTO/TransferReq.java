package com.microservice.transaction.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransferReq {
	
	
	@NotBlank(message = "From Account can not be null")
	private String from_account;
	@NotBlank(message = "To account can not be null")
	private String to_account;
	@NotNull(message = "Amount can not be null")
	private BigDecimal amount;
	
	
	public TransferReq(@NotBlank(message = "From Account can not be null") String from_account,
			@NotBlank(message = "To account can not be null") String to_account,
			@NotNull(message = "Amount can not be null") BigDecimal amount) {
		super();
		this.from_account = from_account;
		this.to_account = to_account;
		this.amount = amount;
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
		return "TransferReq [from_account=" + from_account + ", to_account=" + to_account + ", amount=" + amount + "]";
	}
	
	
	
	
}
