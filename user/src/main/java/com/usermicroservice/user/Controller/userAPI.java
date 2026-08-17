package com.usermicroservice.user.Controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.usermicroservice.user.BusinessError;
import com.usermicroservice.user.DTO.EndUserResponse;
import com.usermicroservice.user.DTO.ResponseData;
import com.usermicroservice.user.DTO.User;
import com.usermicroservice.user.Service.CreateUser;

import jakarta.validation.Valid;


@RestController
public class userAPI {
	
	private static final Logger log = LogManager.getLogger(userAPI.class);
	
	private final CreateUser createuser;
	
	@Autowired
	public userAPI(CreateUser user) {
		
		this.createuser = user;
	}
	
	
	@PostMapping("/create-user")
	public EndUserResponse<ResponseData> createuser(@RequestBody @Valid User user) {
		log.info("Client request in Controller: {}", user);
		
		ResponseData resp = createuser.createuser(user);
		log.info("Response from Service: {}", resp);
		
		EndUserResponse<ResponseData> response = new EndUserResponse<>(BusinessError.SUCCESS, BusinessError.USER_CREATED_MSG, resp);
		return response;
		
		
	}
}
