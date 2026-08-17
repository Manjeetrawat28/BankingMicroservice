package com.bank.notification.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_master")
public class UserMaster {
	
	 	@Id
	    @Column(name = "user_id")
	    private Long userId;

	    @Column(name = "email", nullable = false, unique = true)
	    private String email;

	    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	    private AccountMaster account;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public AccountMaster getAccount() {
			return account;
		}

		public void setAccount(AccountMaster account) {
			this.account = account;
		}
	    
	    

}
