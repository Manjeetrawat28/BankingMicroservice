package com.microservice.transaction.Service;

import java.time.Duration;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.transaction.DTO.EndPointResponseDTO;
import com.microservice.transaction.DTO.UserCreationReqDTO;

import reactor.core.publisher.Mono;


@Service
public class UserClientService {
	
	private final Logger log = LogManager.getLogger();
	private final WebClient webClient;
	

	
	public UserClientService(WebClient.Builder builder, @Value("${user-service-url}") String userURL) {
		this.webClient = builder.baseUrl(userURL).build();
	}
	
	
	
		public Mono<EndPointResponseDTO> createUser(UserCreationReqDTO req) {
			// END POINT SERVICE CALL
			
			Mono<EndPointResponseDTO> resp;
				resp = webClient.post()
					.uri("/create-user")
					.bodyValue(req)
					//.retrieve()
//					.onStatus(HttpStatusCode::is4xxClientError, response ->
//            			response.bodyToMono(EndPointResponseDTO.class)
//            			.flatMap(errBody -> Mono.error(new RuntimeException(errBody.getMsg())))
//            			)
//					.bodyToMono(EndPointResponseDTO.class)
					.exchangeToMono(response ->
	                	response.bodyToMono(EndPointResponseDTO.class)
							)
					.timeout(Duration.ofSeconds(4))
					.doOnSubscribe(sub -> log.info("API call Started"))
					.doOnSuccess(res -> log.info("Response Received: {}", res))
					.doOnError(err ->
                    log.error("User Service Call Failed", err));
				
				return resp;
	}
	

}
