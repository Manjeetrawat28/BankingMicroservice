package com.bank.notification.Consumer;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.bank.notification.DTO.UserData;
import com.bank.notification.Service.SendMailService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class NotificationConsumer {
	
	private static final Logger log = LogManager.getLogger();
	
	@Autowired
	private SendMailService sendMailSvc;
	
	
	@KafkaListener(
			topics= "NOTIFICATION-REQUEST",
			groupId = "notification-request-group",
			containerFactory =  "kafkaListenerContainerFactory",
			concurrency = "3")
	public void consumer(UserData req, Acknowledgment ack) {
		//ObjectMapper mapper = new ObjectMapper(); 
		
		//UserData req = mapper.readValues(message, UserData.class);
		
		log.info("Received Message For txnID: {}", req.getTxnId());
		
		try {
			log.info("Calling Send Email service for TxnID: {}", req.getTxnId());
			sendMailSvc.sendMail(req);
			ack.acknowledge();
			
			
		}catch(Exception ex) {
			log.error("Error Processing Message for TxnId: {}", req.getTxnId());
			throw ex;
		}
	}
}
