package com.bank.notification.Service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bank.notification.EmailTemplate;
import com.bank.notification.DTO.UserData;
import com.bank.notification.Repository.AccountRepository;

@Service
public class MailSenderService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ResponseService responseService;
	
	private static final Logger log = LogManager.getLogger();
	
	@Value("${spring.mail.username}") 
	String email;
	
	@Async
	public boolean sendEmail(String debit_email, String credit_email, UserData req) {
		boolean debitSent = false;
		boolean creditSent = false;
		log.info("Inside the Send Email Block");
		try {
			SimpleMailMessage debit_message = new SimpleMailMessage();
			debit_message.setFrom(email);
			debit_message.setTo(debit_email);
			debit_message.setSubject("Bank Email");
			debit_message.setText(EmailTemplate.DebitEmail(req.getFrom_account(), req.getAmount(), req.getTxnId()));
			mailSender.send(debit_message);
			debitSent = true;
		}catch(MailException ex) {
			log.error("Failed to Send email with error: {}", ex.getMessage());

		}
			
		try {	
			SimpleMailMessage credit_message = new SimpleMailMessage();
			credit_message.setFrom(email);
			credit_message.setTo(credit_email);
			credit_message.setSubject("Bank Email");
			credit_message.setText(EmailTemplate.CreditEmail(req.getTo_account(), req.getAmount(), req.getTxnId()));
			mailSender.send(credit_message);
			creditSent = true;
		}catch(MailException ex) {
			log.error("Failed to Send email with error: {}", ex.getMessage());
		}
		responseService.sendResponse(req, debitSent, creditSent);
		return debitSent && creditSent;
			
	}
	
}
