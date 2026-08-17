package com.usermicroservice.user.DTO;

import jakarta.validation.constraints.Email;

public class UserSearch {
	
	Integer user_id;
	
	@Email(message= "Invalid Email")
	String email;
	String account_number;
	public UserSearch(Integer user_id, @Email(message = "Invalid Email") String email, String account_number) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.account_number = account_number;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	@Override
	public String toString() {
		return "UserSearch [user_id=" + user_id + ", email=" + email + ", account_number=" + account_number + "]";
	}

	
}
