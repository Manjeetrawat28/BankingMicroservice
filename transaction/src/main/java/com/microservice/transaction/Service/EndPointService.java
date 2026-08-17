package com.microservice.transaction.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import com.microservice.transaction.BusinessRules.BusinessError;
import com.microservice.transaction.BusinessRules.BusinessException;
import com.microservice.transaction.DTO.APIResponse;
import com.microservice.transaction.DTO.AccountStatusReq;
import com.microservice.transaction.DTO.BalEnqReq;
import com.microservice.transaction.DTO.EndPointResponseDTO;
import com.microservice.transaction.DTO.PaymentLegReq;
import com.microservice.transaction.DTO.TransferReq;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EndPointService {
	
	
	private final RestClient restClient;
	
	private final Logger log = LogManager.getLogger();
	
	@Autowired
	private SaveTransactionStateService stateSVC;
	
	@Autowired
	private SaveResponseLogService responseLogSVC;
	
	public EndPointService(RestClient restClient, @Value("${account-url}") String url) {
		this.restClient = restClient.mutate()
				.baseUrl(url)
				.build();
	}
	
	
	
	
	public APIResponse<?> verifyAccount(TransferReq req, String txn_id){
		String from_account = req.getFrom_account();
		String to_account = req.getTo_account();
		AccountStatusReq acctStatusReq = new AccountStatusReq(txn_id,from_account, to_account);
		
		
		stateSVC.saveState(txn_id, "Txn_APP", "Acct_SVC", "Account Status Check", acctStatusReq);
		
		
		EndPointResponseDTO response;
		try{
			response = restClient.post()
				.uri("/AccountStatus")
				.body(acctStatusReq)
				.retrieve()
				.body(EndPointResponseDTO.class);
		}catch(RestClientException  ex) {
			 stateSVC.saveState(txn_id, "Txn_APP", "Acct_SVC", "Connection Error", ex.getMessage());
			 
			 responseLogSVC.saveResponseLog(txn_id, "Transfer", "T", "Connection Error",
					 req.getFrom_account(), req.getTo_account(), req.getAmount(), ex.getMessage());
			 
			 throw new BusinessException(
		                BusinessError.ENDPOINT_NOT_REACHABLE,
		                BusinessError.ENDPOINT_NOT_REACHABLE_MSG,
		                null
		        );
		}
		
		if(response == null) {
			stateSVC.saveState(txn_id, "Acct_SVC", "Txn_APP", "Account Status Failed", response);
			
			responseLogSVC.saveResponseLog(txn_id, "Transfer", "F", "No Response From EndPoint",
					 req.getFrom_account(), req.getTo_account(), req.getAmount(), response);
			
			throw new BusinessException(BusinessError.No_DATA_FROM_ENDPOINT, 
					BusinessError.No_DATA_FROM_ENDPOINT_MSG, 
					response);
		}
		
		if(!"00".equals(response.getCode())){
			stateSVC.saveState(txn_id, "Acct_SVC", "Txn_APP", "Account Status Failed", response);
			responseLogSVC.saveResponseLog(txn_id, "Transfer", "F", response.getMsg(),
					 req.getFrom_account(), req.getTo_account(), req.getAmount(), response);
			
			throw new BusinessException(response.getCode(), response.getMsg(), response.getData());
		}
		
		
		APIResponse<?> resp = new APIResponse("00", "S", response.getData());
		
		
		
		return resp;	
		
	}
	
	public APIResponse<?> balEnq(TransferReq reqOrg, String txn_id) {
		String from_account = reqOrg.getFrom_account();
		BalEnqReq req = new BalEnqReq(txn_id,from_account);
		
		EndPointResponseDTO resp;
		
		try {
			stateSVC.saveState(txn_id, "Txn_APP", "Acct_SVC", "BalEnq", req);
			
			resp = restClient.post().uri("/BalEnq").body(req).retrieve().body(EndPointResponseDTO.class);
			
		}catch(RestClientException e) {
			
			stateSVC.saveState(txn_id, "Txn_APP", "Acct_SVC", "Connection Timeout", e.getMessage());
			
			responseLogSVC.saveResponseLog(txn_id, "Transfer", "T", "Connection Error/ Technical Error",
					 reqOrg.getFrom_account(), reqOrg.getTo_account(), reqOrg.getAmount(), e.getMessage());
			
			log.info("Exception in Connection the end point: {}", e);
			throw new BusinessException(BusinessError.ENDPOINT_NOT_REACHABLE, BusinessError.ENDPOINT_NOT_REACHABLE_MSG, e.getMessage());
			
			
		}
		
		if(resp == null) {
			stateSVC.saveState(txn_id, "Acct_SVC", "Txn_APP", "BalEnq Failed", resp);
			responseLogSVC.saveResponseLog(txn_id, "Transfer", "F", "No Response From EndPoint",
					 reqOrg.getFrom_account(), reqOrg.getTo_account(), reqOrg.getAmount(), resp);
			 throw new BusinessException(BusinessError.No_DATA_FROM_ENDPOINT, BusinessError.No_DATA_FROM_ENDPOINT_MSG, resp);
		}
		
		if(!"00".equals(resp.getCode())) {
			// saving msg to transaction state 
			stateSVC.saveState(txn_id, "Acct_SVC", "Txn_APP", "BalEnq Failed", resp);
			//saving msg to response_log
			responseLogSVC.saveResponseLog(txn_id, "Transfer", "F", resp.getMsg(),
					 reqOrg.getFrom_account(), reqOrg.getTo_account(), reqOrg.getAmount(), resp);
			
			throw new BusinessException(resp.getCode(), resp.getMsg(), resp);
			
		}
		
		
		APIResponse response = new APIResponse("00","S", resp.getData());
		return response;
		
		
	}
	
	
	
	// doPayment method -----------------------------
	
	public APIResponse<?> doPayment(TransferReq OrgReq, String txn_id){
		
		PaymentLegReq req = new PaymentLegReq(txn_id, OrgReq.getFrom_account(), OrgReq.getTo_account(), OrgReq.getAmount());
		
		EndPointResponseDTO resp;
		
		try {
			stateSVC.saveState(txn_id, "Txn_APP", "Acct_SVC", "Payment Leg Sent", req);
			resp = restClient.post().uri("/transfer-funds").body(req).retrieve().body(EndPointResponseDTO.class);
			
		}catch(RestClientException ex) {
			stateSVC.saveState(txn_id, "Txn_APP", "Txn_APP", "Connection Timeout", ex.getCause()+ ": " + ex.getMessage());
			
			responseLogSVC.saveResponseLog(txn_id, "Transfer", "T", "Connection Timeout",
					 req.getFrom_account(), OrgReq.getTo_account(), OrgReq.getAmount(), ex.getMessage());
			
			
			throw new BusinessException(BusinessError.ENDPOINT_NOT_REACHABLE, BusinessError.ENDPOINT_NOT_REACHABLE_MSG, ex.getMessage());
		}
		if(resp == null) {
			stateSVC.saveState(txn_id, "Acct_SVC", "Txn_APP", "Payment Leg Failed", resp);
			
			responseLogSVC.saveResponseLog(txn_id, "Transfer", "F", "No Response From EndPoint",
					 req.getFrom_account(), OrgReq.getTo_account(), OrgReq.getAmount(), resp);
			
			throw new BusinessException(BusinessError.No_DATA_FROM_ENDPOINT, BusinessError.No_DATA_FROM_ENDPOINT_MSG, resp);
		}
		if(!"00".equals(resp.getCode())) {
			stateSVC.saveState(txn_id, "Acct_SVC", "Txn_APP", "Payment Leg Failed", resp);
			
			responseLogSVC.saveResponseLog(txn_id, "Transfer", "F", resp.getMsg(),
					 req.getFrom_account(), OrgReq.getTo_account(), OrgReq.getAmount(), resp.getData());
			
			throw new BusinessException(resp.getCode(), resp.getMsg(), resp.getData());
		}
		
		
		APIResponse response =  new APIResponse("00", "S", resp.getData());
		return response;
		
	}
	
}
