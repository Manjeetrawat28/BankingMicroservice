package com.microservice.transaction.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserCreationReqDTO {
	
	@NotBlank(message= "Name can not be NULL")
	private String first_name;
	private String last_name;
	@Email (message= "Invalid Email")
	@NotBlank(message = "Email can not be NULL")
	private String email;
	private BigDecimal initial_amount;
	public UserCreationReqDTO(String first_name, String last_name, String email, BigDecimal initial_amount) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.initial_amount = initial_amount;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public BigDecimal getInitial_amount() {
		return initial_amount;
	}
	public void setInitial_amount(BigDecimal initial_amount) {
		this.initial_amount = initial_amount;
	}
	@Override
	public String toString() {
		return "UserCreationReqDTO [first_name=" + first_name + ", last_name=" + last_name + ", email=" + email
				+ ", initial_amount=" + initial_amount + "]";
	}
	
	
	

}
