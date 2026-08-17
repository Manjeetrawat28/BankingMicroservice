package com.microservice.account.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.Entity.TransactionMasterEntity;
import com.microservice.account.Repository.TransactionMasterRepo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
public class TansactionLogService {
	
	@Autowired
	private TransactionMasterRepo txnRepo;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void logTransaction(String txnId, String fromAccount, String toAccount, BigDecimal amount, char status, String desc) {
		
		TransactionMasterEntity txn = new TransactionMasterEntity();
		
		txn.setTxnId(txnId);
		txn.setFromAcct(fromAccount);
		txn.setToAcct(toAccount);
		txn.setAmount(amount);
		txn.setErrorDesc(desc);
		txn.setStatus(status);
		txn.setCreatedTime(LocalDateTime.now());
		
		entityManager.persist(txn);
	}
}
