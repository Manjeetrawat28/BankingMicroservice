package com.microservice.transaction.Controller;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.transaction.DTO.APIResponse;
import com.microservice.transaction.DTO.TransferReq;
import com.microservice.transaction.Service.NotificationService;
import com.microservice.transaction.Service.TransferMoneyService;

import jakarta.validation.Valid;

@RestController
public class transferController {
	private final Logger log = LogManager.getLogger();
	
	@Autowired
	private TransferMoneyService transferSvc;
	

	@PostMapping("/transfer")
	public APIResponse<?> TransferMoney(@RequestBody @Valid TransferReq req) {
		log.info("Req in Controller: {}", req);
		
		APIResponse response =  transferSvc.moneyTxn(req);
			
		
		return response;
	}
	
	
}
