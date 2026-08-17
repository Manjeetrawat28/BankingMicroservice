package com.usermicroservice.user.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_master")
public class userMasterEntity extends AuditingFileds {
	
	@Id
	@Column(name ="user_id")
	private Integer user_id;
	
	@Column(name ="first_name")
	private String first_name;
	@Column(name = "last_name")
	private String last_name;
	@Column(name = "email")
	private String email;
	
	@Column(name = "account_number")
	private String account_number;
	
	
    public userMasterEntity() {
        // Required by JPA
    }

	

	public userMasterEntity(int user_id, String first_name, String last_name, String email, String account_number) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.account_number = account_number;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	@Override
	public String toString() {
		return "userMasterEntity [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", email=" + email + ", account_number=" + account_number + "]";
	}
	
	
	
	
	
	
	
	
	
}
