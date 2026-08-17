package com.microservice.transaction.Service;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.transaction.DTO.EndPointResponseDTO;



@Service
public class NotificationResponseService {
	
	@Autowired
	private SaveTransactionStateService stateSvc;
	
	private Logger log = LogManager.getLogger();
	
	public void notificationResponse(EndPointResponseDTO res) {
		log.info("Response Received for TxnID: {}", res);
		
		ObjectMapper objectMapper = new ObjectMapper();
		 JsonNode data = objectMapper.valueToTree(res.getData());
		 String txnId = data.get("tnxID").asText();
		 log.info("Response Recived For TxnID : {}", txnId);
		 
		 if(res.getCode().equals("00")) {
			stateSvc.saveState(txnId, "NOTIFICATION_SVC", "Txn_APP", "Notification Success", res);
		 }else {
			 stateSvc.saveState(txnId, "NOTIFICATION_SVC", "Txn_APP", "Notification Failed", res); 
		 }
		
		
	}
}
