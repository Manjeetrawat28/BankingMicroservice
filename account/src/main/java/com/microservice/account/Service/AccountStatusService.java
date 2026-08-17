package com.microservice.account.Service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.APIResponse;
import com.microservice.account.BusinessError;
import com.microservice.account.BusinessException;
import com.microservice.account.DTO.AccountAndStatusDTO;
import com.microservice.account.DTO.Accounts;
import com.microservice.account.Repository.AccountStatusRepo;

@Service
public class AccountStatusService {
	
	private final AccountStatusRepo acctRepo;
	
	@Autowired
	public AccountStatusService(AccountStatusRepo rep) {
		this.acctRepo = rep;
	}
	@Autowired 
	public TansactionLogService txnLogSvc;
	public APIResponse<?> accountStatus(Accounts req) {
		
		AccountAndStatusDTO fromAcctDet =  acctRepo.accountStatus(req.getFrom_account());
		AccountAndStatusDTO toAcctDet = acctRepo.accountStatus(req.getTo_account());
		
		
		if(fromAcctDet == null || toAcctDet == null) {
			if(fromAcctDet == null && toAcctDet == null ) {
				txnLogSvc.logTransaction(req.getTxnId()+"@check", req.getFrom_account(), req.getTo_account(), BigDecimal.ZERO, 'F', "No Account Found");
				throw new BusinessException(
						BusinessError.No_Account_Found, 
						BusinessError.No_Account_Found_MSG + " with account numbers: "+ req.getFrom_account() + ", " + req.getTo_account(), 
						req);
			}
			else if (fromAcctDet == null && toAcctDet != null) {
				txnLogSvc.logTransaction(req.getTxnId()+"@check", req.getFrom_account(), req.getTo_account(), BigDecimal.ZERO, 'F', "From Account not Found");
				throw new BusinessException(
						BusinessError.No_Account_Found, 
						BusinessError.No_Account_Found_MSG + " with account number: "+ req.getFrom_account(), 
						req);
			}else {
				txnLogSvc.logTransaction(req.getTxnId()+"@check", req.getFrom_account(), req.getTo_account(), BigDecimal.ZERO, 'F', "To Account not Found");
				throw new BusinessException(
					
					BusinessError.No_Account_Found, 
					BusinessError.No_Account_Found_MSG + " with account number: "+ req.getTo_account(), 
					req);
			}	
		}
		
		
		else if (fromAcctDet != null && fromAcctDet.getStatus().equalsIgnoreCase("Inactive")){
			txnLogSvc.logTransaction(req.getTxnId()+"@check", req.getFrom_account(), req.getTo_account(), BigDecimal.ZERO, 'F', "From Account is Inactive");
			throw new BusinessException(BusinessError.Accout_Status, 
					BusinessError.Accout_Status_MSG + ": " + req.getFrom_account(),
					req);
		}
		
		
		
		else if (toAcctDet !=  null && toAcctDet.getStatus().equalsIgnoreCase("Inactive")) {
			txnLogSvc.logTransaction(req.getTxnId()+"@check", req.getFrom_account(), req.getTo_account(), BigDecimal.ZERO, 'F', "To Account not Found");
			throw new BusinessException(BusinessError.Accout_Status, 
					BusinessError.Accout_Status_MSG + ": " + req.getTo_account(),
					req);
			
			
			
		}else {
			txnLogSvc.logTransaction(req.getTxnId()+"@check", req.getFrom_account(), req.getTo_account(), BigDecimal.ZERO, 'S', "Success");
			APIResponse<?> resp = new APIResponse("00", "Success", req);
			return resp;
		}
	}
}
