package com.microservice.account.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.APIResponse;
import com.microservice.account.BusinessError;
import com.microservice.account.DTO.Accounts;
import com.microservice.account.Service.AccountStatusService;
	
import jakarta.validation.Valid;

@RestController
public class AccountStatusController {
	
	private final AccountStatusService acctSvc;
	
	@Autowired
	public AccountStatusController(AccountStatusService svc) {
		this.acctSvc = svc;
	}
	
	@PostMapping("/AccountStatus")
	public ResponseEntity<APIResponse<?>> accountStatus(@RequestBody @Valid Accounts req) {
		
		APIResponse<?> res = acctSvc.accountStatus(req);
		APIResponse<?> resp = new APIResponse(BusinessError.SUCCESS, BusinessError.Success_MSG, res.getData());
		return ResponseEntity.ok().body(resp);

	}
}
