package com.bank.notification.Service;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bank.notification.DTO.ResponseDTO;
import com.bank.notification.DTO.UserData;
import com.bank.notification.DTO.ResponseBody;

@Service
public class ResponseService {
	
	@Autowired
	private KafkaTemplate<String, ResponseDTO<?>> kafkaTemplate;
	
	private static final String TOPIC =  "NOTIFICATION-RESPONSE";
	
	private Logger log = LogManager.getLogger();
	
	public <T>void sendResponse(UserData req, boolean debitMsg, boolean creditMsg) {
		
		ResponseDTO res =  new ResponseDTO<>(); 
		
		if(debitMsg) {
			String code = "00";
			String msg = "Success";
			ResponseBody body = new ResponseBody(req.getTxnId(), "Email Sent Successfully");
			res = new ResponseDTO<>(code, msg, body); 
		}
		else {
			String code = "11";
			String msg = "Failure";
			ResponseBody body = new ResponseBody(req.getTxnId(), "Email Send Failed");
			res = new ResponseDTO<>(code, msg, body);
		}
		
		try {
			kafkaTemplate.send(TOPIC, req.getTxnId(), res)
			.whenComplete((result, ex) -> {
				if(ex == null) {
					log.info("Message Send Succefully to kafka topic: {}", TOPIC);
				}else {
					log.error("Failed to Send MSG to kafka topic: {}", TOPIC);
					log.error("Exception: {}", ex.getMessage());
				}
			});
			
		}catch(Exception ex) {
			log.error("Exception Occured: {}", ex.getMessage());
		}
		
	}
	
}
