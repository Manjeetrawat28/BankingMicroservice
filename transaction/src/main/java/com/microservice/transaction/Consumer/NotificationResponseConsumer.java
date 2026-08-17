package com.microservice.transaction.Consumer;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import com.microservice.transaction.Service.NotificationResponseService;

import com.microservice.transaction.DTO.EndPointResponseDTO;

@Component
public class NotificationResponseConsumer {
	
	private Logger log = LogManager.getLogger();
	@Autowired
	private NotificationResponseService NotificationResponseService;
	
	@KafkaListener(
			topics="NOTIFICATION-RESPONSE",
			groupId="notification-response-group",
			containerFactory = "kafkaListenerContainerFactory",
			concurrency = "3")
	public void notificationResponse(EndPointResponseDTO res, Acknowledgment ack ) {
		log.info("Received Message For txnID: {}", res.getData());
		
		try {
			NotificationResponseService.notificationResponse(res);
			log.info("Sent to Notification Service");
			ack.acknowledge();
		}
		catch(Exception e) {
			log.info("Notification Service failed, Exception: {}", e.getMessage());
			throw e;
		}
		
	}
}
