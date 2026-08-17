package com.microservice.account.DTO;

public class AccountAndStatusDTO {
	String account_number;
	String Status;
	public AccountAndStatusDTO(String account_number, String status) {
		super();
		this.account_number = account_number;
		Status = status;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	
	
}
