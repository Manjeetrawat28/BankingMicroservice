package com.microservice.account.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "transaction_master")
public class TransactionMasterEntity {
	
	@Id
	@Column(name = "txn_id")
	private String txnId;
	
	@Column(name = "from_account")
	private String fromAcct;
	@Column(name = "to_account")
	private String toAcct;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name ="status")
	private char status;
	@Column(name = "error_description")
	private String errorDesc;
	@Column(name = "created_time")
	private LocalDateTime createdTime;
	
	
	public TransactionMasterEntity() {}
	
	
	public TransactionMasterEntity(String txnId, String fromAcct, String toAcct, BigDecimal amount, char status,
			String errorDesc, LocalDateTime createdTime) {
		super();
		this.txnId = txnId;
		this.fromAcct = fromAcct;
		this.toAcct = toAcct;
		this.amount = amount;
		this.status = status;
		this.errorDesc = errorDesc;
		this.createdTime = createdTime;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getFromAcct() {
		return fromAcct;
	}
	public void setFromAcct(String fromAcct) {
		this.fromAcct = fromAcct;
	}
	public String getToAcct() {
		return toAcct;
	}
	public void setToAcct(String toAcct) {
		this.toAcct = toAcct;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}
	@Override
	public String toString() {
		return "TransactionMasterEntity [txnId=" + txnId + ", fromAcct=" + fromAcct + ", toAcct=" + toAcct + ", amount="
				+ amount + ", status=" + status + ", errorDesc=" + errorDesc + ", createdTime=" + createdTime + "]";
	}
	
	
	
}
