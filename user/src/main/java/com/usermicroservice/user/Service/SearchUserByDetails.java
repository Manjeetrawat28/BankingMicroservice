package com.usermicroservice.user.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usermicroservice.user.BusinessError;
import com.usermicroservice.user.BusinessExceptions;
import com.usermicroservice.user.Controller.userAPI;
import com.usermicroservice.user.DTO.UserDetailsDTO;
import com.usermicroservice.user.DTO.UserSearch;
import com.usermicroservice.user.Repository.SearchUserRepo;

@Service
public class SearchUserByDetails {
	
	private final SearchUserRepo searchUser;
	private static final Logger log = LogManager.getLogger(SearchUserByDetails.class);
	
	@Autowired
	public SearchUserByDetails(SearchUserRepo usr) {
		this.searchUser = usr;
	}
	
	
	public UserDetailsDTO searchUser(UserSearch req) {
		
		log.info("Seach var in req: {}", req);
		if(req.getEmail() == null && req.getUser_id() == null && req.getAccount_number() == null) {
			throw new BusinessExceptions(BusinessError.No_data_in_request, BusinessError.No_data_in_request_MSG, null);
		}else { 
		
			UserDetailsDTO userDT = searchUser.findUserDetails(req.getEmail(), req.getUser_id(), req.getAccount_number());
			if(userDT == null) {
				throw new BusinessExceptions(BusinessError.No_User_Found, BusinessError.No_User_Found_MSG, null);
			}else return userDT;
		}
				
	}
		
}
