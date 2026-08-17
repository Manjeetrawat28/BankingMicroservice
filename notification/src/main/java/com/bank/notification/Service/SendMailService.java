package com.bank.notification.Service;

import java.util.Optional;

import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.notification.DTO.UserData;
import com.bank.notification.Repository.AccountRepository;


@Service
public class SendMailService {
	
	
	@Autowired
	private AccountRepository acctRepo;
	
	@Autowired 
	private MailSenderService mailSendSvc;
	
	private static final Logger log = LogManager.getLogger();
	
	public void sendMail(UserData req) {
		
		log.info("Request Received For Send Email Service, TxnId: {}", req.getTxnId());
		String from_account =  req.getFrom_account();
		String to_account =  req.getTo_account();
		log.info("Fetching Deatils of Email from Db");
		String debit_email =  acctRepo.findEmaiByAcct(from_account);
		String credit_email = acctRepo.findEmaiByAcct(to_account);
		log.info(" Deatils Featched");
		
		
		boolean sent = mailSendSvc.sendEmail(debit_email, credit_email, req);
		
		if(sent) {
			log.info("Email Sent Succefully");
		}
		
		
	}

}
