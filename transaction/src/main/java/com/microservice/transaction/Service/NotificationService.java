package com.microservice.transaction.Service;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.microservice.transaction.DTO.NotificationDTO;
import com.microservice.transaction.DTO.TransferReq;

@Service
public class NotificationService {
	
	
	
	@Autowired
	private KafkaTemplate<String, NotificationDTO> kafkatemplate;
	
	private static final String TOPIC =  "NOTIFICATION-REQUEST";
	
	@Autowired
	private SaveTransactionStateService stateSVC;
	
	private Logger log = LogManager.getLogger();
	
	public void sendEmail(String txnId, TransferReq data) {
		
		NotificationDTO req = new NotificationDTO(txnId, data.getAmount(), data.getTo_account(), data.getFrom_account());
		
		try {
			kafkatemplate.send(TOPIC, txnId, req)
				.whenComplete((result, ex) -> {
					if(ex == null) {
						log.info("Message Send Succefully to kafka topic: {}", TOPIC);
						stateSVC.saveState(txnId, "Acct_SVC", "NOTIFICATION_SVC", "Send Email", req);
						
					}else {
						log.error("Failed to Send Message to Kafka: {}", ex.getMessage());
						stateSVC.saveState(txnId, "Acct_SVC", "NOTIFICATION_SVC", "Send Email Fail", ex.getMessage());
					}
				});
		}catch(Exception e) {
			log.error("Kafka publish failed (sync failure) for txnId={}", txnId, e);
			stateSVC.saveState(txnId, "Acct_SVC", "NOTIFICATION_SVC", "Send Email Fail", e.getMessage());
		}
	}
}
