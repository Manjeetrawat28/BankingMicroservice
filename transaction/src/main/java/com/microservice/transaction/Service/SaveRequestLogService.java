package com.microservice.transaction.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.transaction.Entity.RequestLog;
import com.microservice.transaction.Repository.RequestLogRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Service
public class SaveRequestLogService {
	
	@Autowired
	private RequestLogRepo logRepo;
	@PersistenceContext
	private EntityManager entityManager;
	
	
	 private final ObjectMapper objectMapper;

	    public SaveRequestLogService(ObjectMapper objectMapper) {
	        this.objectMapper = objectMapper;
	    }
	    
	    
	@Transactional
	public void saveRequest(String txn_id, String txnType, Object req) {
		
		try {
		RequestLog data = new RequestLog();
		
		data.setTxnId(txn_id);
		data.setTxnType(txnType);
		data.setCreatedTime(LocalDateTime.now());
		
		String json = objectMapper.writeValueAsString(req);

		data.setOriginalReq(json);
		
		entityManager.persist(data);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
}
