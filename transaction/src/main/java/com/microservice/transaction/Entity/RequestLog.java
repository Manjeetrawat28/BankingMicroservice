package com.microservice.transaction.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "request_log")
public class RequestLog {
	@Id
    @Column(name = "txn_id")
    private String txnId;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @Column(name = "txn_type")
    private String txnType;


    @Column(name = "original_req", columnDefinition = "json")
    private String originalReq;

    public RequestLog() {}

    public RequestLog(String txnId, LocalDateTime createdTime, String txnType, String originalReq) {
        this.txnId = txnId;
        this.createdTime = createdTime;
        this.txnType = txnType;
        this.originalReq = originalReq;
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

    public String getOriginalReq() {
        return originalReq;
    }

    public void setOriginalReq(String originalReq) {
        this.originalReq = originalReq;
    }
}
