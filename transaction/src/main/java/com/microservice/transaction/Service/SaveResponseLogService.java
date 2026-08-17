package com.microservice.transaction.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.transaction.Entity.ResponseLog;
import com.microservice.transaction.Repository.ResponseLogRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class SaveResponseLogService {
	
	@Autowired
	private ResponseLogRepo respLog;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final ObjectMapper objectMapper;
	
	public SaveResponseLogService(ObjectMapper mapper) {
		this.objectMapper = mapper;
	}
	
	@Transactional
	public void saveResponseLog(String txnId, String txnType, String status, String errDesc, String fromAcct, String toAccr, BigDecimal amount , Object resp) {
		ResponseLog data = new ResponseLog();
		
		try {
		data.setTxnId(txnId);
		data.setTxnType(txnType);
		data.setStatus(status);
		data.setErrorDesc(errDesc);
		data.setFromAccount(fromAcct);
		data.setToAccount(toAccr);
		data.setAmount(amount);
		data.setCreatedTime(LocalDateTime.now());
		
		String json = objectMapper.writeValueAsString(resp);
		data.setResponse(json);
		entityManager.persist(data);
		
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	

}
