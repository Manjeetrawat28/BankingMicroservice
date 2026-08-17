package com.usermicroservice.user.DTO;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;

public class User {
	
	@NotNull(message= "Name can not be empty")
	@NotBlank(message= "Name can not be empty")
	String first_name;
	String last_name;
	
	@Email(message = "Invalid Email")
	@NotNull
	@NotBlank(message= "Email field can not be empty")
	String email;
	BigDecimal initial_amount;

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
		return "User [first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + ", initial_amount="
				+ initial_amount + "]";
	}


	
	
}
