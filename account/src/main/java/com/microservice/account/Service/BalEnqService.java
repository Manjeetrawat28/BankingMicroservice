package com.microservice.account.Service;

import java.math.BigDecimal;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.BusinessError;
import com.microservice.account.BusinessException;
import com.microservice.account.DTO.BalEnqResp;
import com.microservice.account.DTO.BalReqDTO;
import com.microservice.account.Repository.FetchBalRepo;

@Service
public class BalEnqService {
	
	
	private final Logger log = LogManager.getLogger();
	
	private final FetchBalRepo fetchBalRepo;
	
	@Autowired
	public BalEnqService(FetchBalRepo fetchBalRepo) {
		this.fetchBalRepo = fetchBalRepo;
	}
	
	@Autowired
	public TansactionLogService txnLogService;
	
	public BalEnqResp getBalance(BalReqDTO req) {
		log.info("Request Received in Service Layer: {}", req);
		
		if(req.getAccount_number() ==  null && req.getUser_id() == null) {
			log.info("This is First IF cndtn");
			txnLogService.logTransaction(req.getTxnId()+"@balEnq", req.getAccount_number(), "", BigDecimal.ZERO, 'F', "No data in Request" );
			
			throw new BusinessException(BusinessError.No_data_in_Request, BusinessError.No_Data_in_Request, req);
			
		}
		BalEnqResp resp =  fetchBalRepo.FetchBal(req.getUser_id(), req.getAccount_number());
		if(resp == null) {
			log.info("This is 2nd IF cndtn");
			txnLogService.logTransaction(req.getTxnId()+"@balEnq", req.getAccount_number(), "", BigDecimal.ZERO, 'F', "No Account Found" );
				throw new BusinessException(BusinessError.No_Account_Found, BusinessError.No_Account_Found_MSG, req );
			}else {
				resp.setTxnId(req.getTxnId()+"@balEnq");
				log.info("This is else cndtn");
				txnLogService.logTransaction(req.getTxnId()+"@balEnq", req.getAccount_number(), "", BigDecimal.ZERO, 'S', "Success" );
				return resp;
			}
		
	}

}
