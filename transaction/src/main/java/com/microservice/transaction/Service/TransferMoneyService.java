package com.microservice.transaction.Service;

import java.math.BigDecimal;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.transaction.BusinessRules.BusinessError;
import com.microservice.transaction.DTO.APIResponse;
import com.microservice.transaction.DTO.AccountStatusReq;
import com.microservice.transaction.DTO.BalEnqReq;
import com.microservice.transaction.DTO.PaymentLegReq;
import com.microservice.transaction.DTO.TransferReq;
import com.microservice.transaction.Utility.GenerateTxnID;

@Service
public class TransferMoneyService {
	private final Logger log = LogManager.getLogger();
	
	@Autowired
	private SaveRequestLogService requestTable;
	
	@Autowired
	private EndPointService endPointService;
	
	@Autowired 
	private SaveResponseLogService responseLogSVC;
	
	@Autowired
	private SaveTransactionStateService stateSVC;
	
	@Autowired
	private NotificationService notifySvc;
	
	
	public APIResponse<?> moneyTxn(TransferReq req) {
		
		String txn_id = GenerateTxnID.generateTxnId();
		requestTable.saveRequest(txn_id, "Transfer", req);
		
		APIResponse verifyResp = endPointService.verifyAccount(req, txn_id);
		stateSVC.saveState(txn_id, "Acct_SVC", "Txn_APP", "Account Status Success", verifyResp);
		log.info("Response for Account Verification: {}", verifyResp);
		
		APIResponse balEnqResp = endPointService.balEnq(req, txn_id);
		stateSVC.saveState(txn_id, "Acct_SVC", "Txn_APP", "BalEnq Success", balEnqResp);
		log.info("Response for Balance Enq ", balEnqResp);
		
		
		APIResponse paymentResp = endPointService.doPayment(req, txn_id);
		stateSVC.saveState(txn_id, "Acct_SVC", "Txn_APP", "Payment Leg Recived", paymentResp);
		log.info("Response for Payement Leg ", paymentResp);
		
		if(verifyResp.getErrorCode().equals("00") && balEnqResp.getErrorCode().equals("00")
				&& paymentResp.getErrorCode().endsWith("00") ) {
			
			APIResponse response = new APIResponse(BusinessError.SUCCESS, "Success", paymentResp.getData());
			
			responseLogSVC.saveResponseLog(txn_id, "Transfer", "S", "Success", req.getFrom_account(), req.getTo_account(), req.getAmount()
					, response);
			log.info("Invoking Email Service");
			
			notifySvc.sendEmail(txn_id, req);
			
			return response;
		}else {
			APIResponse response = new APIResponse(BusinessError.SYSTEM_ERROR, BusinessError.SYSTEM_ERROR_MSG, null);
			responseLogSVC.saveResponseLog(txn_id, "Transfer", "F", "Something went Wrong", req.getFrom_account(), req.getTo_account(), req.getAmount()
					, response.getData());
			return response;
		}
		
	}
	
}
