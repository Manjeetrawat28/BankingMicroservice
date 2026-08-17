package com.microservice.account.Service;

import java.math.BigDecimal;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.APIResponse;
import com.microservice.account.BusinessError;
import com.microservice.account.BusinessException;
import com.microservice.account.DTO.TransferReqDTO;
import com.microservice.account.Entity.AccountMasterEntity;
import com.microservice.account.Entity.TransactionMasterEntity;
import com.microservice.account.Repository.AccountStatusRepo;

import jakarta.transaction.Transactional;

@Service
public class transferService {
	private static final Logger log = LogManager.getLogger();
	
	
	@Autowired
	private AccountStatusRepo acctRepo;
	
	@Autowired
	private TansactionLogService txnLogsvc;
	
	
	@Transactional
	public APIResponse<?> TransferFunds(TransferReqDTO req){
		
		String txnId = req.getTxn_id();
		String from_account = req.getFrom_account();
		String to_account = req.getTo_account();
		BigDecimal amount =  req.getAmount();		
		
		try {
			AccountMasterEntity source = acctRepo.findAccountByAccountNumber(from_account);
			AccountMasterEntity dest = acctRepo.findAccountByAccountNumber(to_account);
			//Thread.sleep(4000);
			if(source == null || dest == null) {
				
				txnLogsvc.logTransaction(txnId+"@txn", from_account,to_account, amount, 'F',"Account Not Found" );
				
				throw new BusinessException(
					BusinessError.No_Account_Found, 
					BusinessError.No_Account_Found_MSG, 
					req);
			}
			
			if(source.getStatus().equalsIgnoreCase("Inactive") || dest.getStatus().equalsIgnoreCase("Inactive")) {
				
				txnLogsvc.logTransaction(txnId+"@txn", from_account,to_account, amount, 'F',"Account Not Active" );
				
				throw new BusinessException(BusinessError.Accout_Status, BusinessError.Accout_Status_MSG, req);
			}
			
			
			BigDecimal curr_amount = source.getAmount();
			if( curr_amount == null || curr_amount.compareTo(BigDecimal.ZERO) == 0 || curr_amount.compareTo(amount) < 0) { 
				
					txnLogsvc.logTransaction(txnId+"@txn", from_account,to_account, amount, 'F',"Insufficient Balance" );
					throw new BusinessException(BusinessError.OverDue, BusinessError.OverDue_MSG , req); 
			}
			
			source.setAmount(curr_amount.subtract(amount));
			dest.setAmount(dest.getAmount().add(amount));
			
			txnLogsvc.logTransaction(txnId+"@txn", from_account,to_account, amount, 'S',"Success" );
			
			
			APIResponse<?> resp = new APIResponse(BusinessError.SUCCESS, BusinessError.Success_MSG, req);
			return resp;

		}catch(BusinessException ex) {
			throw ex;
		}
		catch (Exception ex ) {
			txnLogsvc.logTransaction(txnId+"@txn", from_account,to_account, amount, 'T',"Failed Due to Technical Error" );
			throw ex;
		}
	}
	
}
