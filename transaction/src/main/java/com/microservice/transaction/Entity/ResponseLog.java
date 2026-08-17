package com.microservice.transaction.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "response_log")
public class ResponseLog {

    @Id
    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "txn_type")
    private String txnType;

    @Column(name = "status")
    private String status;

    @Column(name = "error_desc")
    private String errorDesc;

    @Column(name = "from_account")
    private String fromAccount;

    @Column(name = "to_account")
    private String toAccount;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "response", columnDefinition = "json")
    private String response;

    public ResponseLog() {}

    public ResponseLog(String txnId, LocalDateTime createdTime, String txnType,
                       String status, String errorDesc,
                       String fromAccount, String toAccount,
                       BigDecimal amount, String response) {

        this.txnId = txnId;
        this.createdTime = createdTime;
        this.txnType = txnType;
        this.status = status;
        this.errorDesc = errorDesc;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.response = response;
    }

    public String getTxnId() {
        return txnId;
    }

    public void setTxnId(String txnId) {
        this.txnId = txnId;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
