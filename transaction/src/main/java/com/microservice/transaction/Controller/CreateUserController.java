package com.microservice.transaction.Controller;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.transaction.DTO.APIResponse;
import com.microservice.transaction.DTO.EndPointResponseDTO;
import com.microservice.transaction.DTO.UserCreationReqDTO;
import com.microservice.transaction.Service.UserClientService;
import com.microservice.transaction.Service.UserCreationService;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
public class CreateUserController {
	
	private final Logger log = LogManager.getLogger();
	
	@Autowired
	private UserCreationService createUserSvc;
	
	@PostMapping("/create-user")
	public Mono<APIResponse> createUser(@RequestBody @Valid UserCreationReqDTO user ) {
		
		log.info("Request in user Creation Controller: {}", user);
		Mono<APIResponse>  resp = createUserSvc.createUser(user);
		return resp;
	}
}
