package com.usermicroservice.user.DTO;

public class ResponseData {
	Integer user_id;
	String account_number;
	String name;
	String last_name;
	String email;
	
	
	

	public ResponseData(Integer user_id, String account_number, String name, String last_name, String email) {
		super();
		this.user_id = user_id;
		this.account_number = account_number;
		this.name = name;
		this.last_name = last_name;
		this.email = email;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	@Override
	public String toString() {
		return "ResponseData [user_id=" + user_id + ", account_number=" + account_number + ", name=" + name
				+ ", last_name=" + last_name + ", email=" + email + "]";
	}
	
	
	
}
