package com.microservice.account.Controller;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.microservice.account.APIResponse;
import com.microservice.account.DTO.TransferReqDTO;
import com.microservice.account.Service.transferService;

import jakarta.validation.Valid;

@RestController
public class TransferController {
	
	private static final Logger log = LogManager.getLogger();
	
	private final transferService transferSvc;
	
	@Autowired
	public TransferController(transferService svc) {
		this.transferSvc = svc;
	}
	
	@PostMapping("/transfer-funds")
	public APIResponse<?> transferFunds(@Valid @RequestBody TransferReqDTO req) {
		
		log.info("Request Recived: {}", req);
		return transferSvc.TransferFunds(req);
		
	}
}
