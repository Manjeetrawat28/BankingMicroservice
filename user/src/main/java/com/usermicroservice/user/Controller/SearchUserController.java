package com.usermicroservice.user.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.usermicroservice.user.BusinessError;
import com.usermicroservice.user.DTO.EndUserResponse;
import com.usermicroservice.user.DTO.UserDetailsDTO;
import com.usermicroservice.user.DTO.UserSearch;
import com.usermicroservice.user.Service.SearchUserByDetails;

import jakarta.validation.Valid;

@RestController
public class SearchUserController {
	
	private final SearchUserByDetails searchSvc;
	
	@Autowired
	public SearchUserController(SearchUserByDetails use) {
		this.searchSvc = use;
	}
	
	@PostMapping("/search-user")
	public EndUserResponse<UserDetailsDTO> searchUser(@RequestBody @Valid UserSearch req){
		UserDetailsDTO deatils =  searchSvc.searchUser(req);
		
		EndUserResponse<UserDetailsDTO> resp= new EndUserResponse<>(BusinessError.SUCCESS, BusinessError.User_Found_MSG, deatils);
		
		return resp;
		
	}
	
}
