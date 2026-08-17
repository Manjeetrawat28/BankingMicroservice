package com.usermicroservice.user.Service;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.usermicroservice.user.BusinessError;
import com.usermicroservice.user.BusinessExceptions;
import com.usermicroservice.user.DTO.EndUserResponse;
import com.usermicroservice.user.DTO.ResponseData;
import com.usermicroservice.user.DTO.User;
import com.usermicroservice.user.Entity.AccountMasterEntity;
import com.usermicroservice.user.Entity.userMasterEntity;
import com.usermicroservice.user.Repository.UserRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CreateUser {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final UserRepo userRepo;
	
	@Autowired 
	public CreateUser(UserRepo usr) {
		this.userRepo = usr;
	}
	
	private static final Logger log = LogManager.getLogger(CreateUser.class);
	
	
	
	public ResponseData createuser(User req){
		log.info("Client request: {}", req);
		
		String email = req.getEmail().toLowerCase().trim();
			Optional<userMasterEntity> user =  userRepo.findByEmail(email);
			if(user.isPresent()) {
				ResponseData data = new ResponseData((Integer) null, null, req.getFirst_name(), req.getLast_name(), email);
				throw new BusinessExceptions(BusinessError.DUPLICATE_EMAIL, BusinessError.DUPLICATE_EMAIL_MSG, data);
			}
				
			
			userMasterEntity userDetails = generatUserDeatils(req);
			AccountMasterEntity acctMast = fillMasterDeatils(userDetails);
			if(req.getInitial_amount() == null) {
				BigDecimal amount = new BigDecimal("00.00");
				acctMast.setAmount(amount);
			}else {
				acctMast.setAmount(req.getInitial_amount());
			}
			
			entityManager.persist(userDetails);
			entityManager.persist(acctMast);
			entityManager.flush();
			ResponseData resp = new ResponseData(userDetails.getUser_id(), 
					userDetails.getAccount_number(), 
					userDetails.getFirst_name(), 
					userDetails.getLast_name(),
					userDetails.getEmail());
				
			return resp;
				
			}
		
	
	
	
	
	
	
	

	
	
	                          /* Private Methods */
	// Generate user Details
	private userMasterEntity generatUserDeatils(User req) {
		userMasterEntity ue =  user_to_entity(req);
		int user_id = generateUser();
		String account_no =  generateAcct();
		ue.setAccount_number(account_no);
		ue.setUser_id(user_id);
		
		return ue;
	}
	
	// Generate User ID
	;
	private int generateUser() {
		int user = ThreadLocalRandom.current().nextInt(10000, 100000);;
		return user;
		//return 10458;
	}
	// Generate Account Number
	private String generateAcct() {
		 SecureRandom random = new SecureRandom();

	        long number = 1_000_000_000L + 
	                     (long)(random.nextDouble() * 9_000_000_000L);

	        String acct = String.valueOf(number);
		return acct;
	       // return "3810663603";
	}
	
	// Fill Account Master Table details
	private AccountMasterEntity fillMasterDeatils(userMasterEntity usr) {
		//AccountMasterEntity acct = new AccountMasterEntity(null, null, null, "Active");
		String account_number = usr.getAccount_number();
		Integer user_id = usr.getUser_id();
		AccountMasterEntity acct = new AccountMasterEntity(account_number, user_id, null, "Active");
		return acct;
	}
	
	
	// Mapper Method
	private userMasterEntity user_to_entity(User req) {
		userMasterEntity en = new userMasterEntity(0, null, null, null, null);
		en.setFirst_name(req.getFirst_name());
		en.setLast_name(req.getLast_name());
		en.setEmail(req.getEmail());
		
		return en;
	}
	
	// Entity to Response
//	private ResponseData entityToResponse(userMasterEntity en) {
//		
//		String acct = en.getAccount_number();
//		String email = en.getEmail();
//		int id = en.getUser_id();
//		String name =en.getFirst_name();
//		ResponseData resp = new ResponseData(id, acct, name, email);
//		return resp;
//		
//	}
	
}
