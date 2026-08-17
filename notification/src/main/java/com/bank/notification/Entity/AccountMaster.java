package com.bank.notification.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "account_master")
public class AccountMaster {
	
	  	@Id
	    @Column(name = "account_number")
	    private String accountNumber;

	    @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false, unique = true)
	    private UserMaster user;

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public UserMaster getUser() {
			return user;
		}

		public void setUser(UserMaster user) {
			this.user = user;
		}
	    
	    
}
