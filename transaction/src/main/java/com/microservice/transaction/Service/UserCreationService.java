package com.microservice.transaction.Service;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.transaction.DTO.APIResponse;
import com.microservice.transaction.DTO.EndPointResponseDTO;
import com.microservice.transaction.DTO.UserCreationReqDTO;

import reactor.core.publisher.Mono;

@Service
public class UserCreationService {
	
	private final Logger log = LogManager.getLogger();

	private final UserClientService userClientSvc;
	public UserCreationService(UserClientService svc) {
		this.userClientSvc = svc;
	}
	
	public Mono<APIResponse> createUser(UserCreationReqDTO req) {
		
		Mono<EndPointResponseDTO> resp = userClientSvc.createUser(req);
		
		return resp.map(res -> {
			if("00".equals(res.getCode())) {
				return new APIResponse("00", "User Created", res.getData());
			}
			if("U1".equals(res.getCode())) {
				return new APIResponse("D1", "Failed", "User Exists With Provided Email");
			}
			return new APIResponse(res.getCode(), res.getMsg(), res.getData());
		})
		.switchIfEmpty(Mono.just(new APIResponse("E1", "Empty Response", null)))
		.onErrorResume(ex -> {
		    log.error("User service failed", ex);
		    return Mono.just(new APIResponse("E2", "Technical Error", null));
		});
		
	}

}
