package com.microservice.account.Controller;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.APIResponse;
import com.microservice.account.BusinessError;
import com.microservice.account.DTO.BalEnqResp;
import com.microservice.account.DTO.BalReqDTO;
import com.microservice.account.Service.BalEnqService;

@RestController
public class BalEnqController {
	
	public static final Logger log = LogManager.getLogger();
	private final BalEnqService balsvc;
	
	@Autowired 
	public BalEnqController(BalEnqService svc) {
		this.balsvc = svc;
	}
	
	@PostMapping("/BalEnq")
	public APIResponse<BalEnqResp> getBal(@RequestBody BalReqDTO req) {
		log.info("Request Received in Controller: {}", req);
		
		BalEnqResp resp =  balsvc.getBalance(req);
		
		APIResponse<BalEnqResp> res =  new APIResponse<BalEnqResp>(BusinessError.SUCCESS, BusinessError.Success_MSG, resp);
		return res;
	}
	
}
