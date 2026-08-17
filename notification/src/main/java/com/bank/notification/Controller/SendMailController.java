package com.bank.notification.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.notification.DTO.UserData;
import com.bank.notification.Service.SendMailService;

@RestController
public class SendMailController {
	
	@Autowired
	private SendMailService sendMailSvc;
	
	
	@PostMapping("/sendEmail")
	public String sendEmail(@RequestBody UserData req) {
	
		sendMailSvc.sendMail(req);
		//String ans = mailSvc.sendMain(email);
		
		return "ans";
	}

}
