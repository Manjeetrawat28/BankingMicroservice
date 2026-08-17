package com.usermicroservice.user.Entity;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component 
public class getUser implements AuditorAware <String>{
	@Override
	public Optional<String> getCurrentAuditor(){
		// later this can come from Spring Security
		//SecurityContextHolder.getContext().getAuthentication().getName();

		
		return Optional.of("Application");
	}
}
