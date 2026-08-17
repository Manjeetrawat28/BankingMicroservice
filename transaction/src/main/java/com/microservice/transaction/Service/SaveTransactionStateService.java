package com.microservice.transaction.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.transaction.Entity.TransactionState;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class SaveTransactionStateService {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	 private final ObjectMapper objectMapper;

	    public SaveTransactionStateService(ObjectMapper objectMapper) {
	        this.objectMapper = objectMapper;
	    }
	    
	  @Transactional
	  public void saveState(String txnId, String source, String dest, String leg, Object data) {
		  
		  try {
			  TransactionState state = new TransactionState();
			  state.setTxnId(txnId);
			  state.setSource(source);
			  state.setDestination(dest);
			  state.setCreatedTime(LocalDateTime.now());
			  state.setLeg(leg);
			  String json = objectMapper.writeValueAsString(data);
			  state.setData(json);
			  
			  entityManager.persist(state);
			  
		  }catch(Exception ex) {
			  throw new RuntimeException(ex);
		  }
		  
	  }
	
}
