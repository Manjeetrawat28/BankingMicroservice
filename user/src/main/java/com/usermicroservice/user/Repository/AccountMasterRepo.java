package com.usermicroservice.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermicroservice.user.Entity.AccountMasterEntity;

@Repository
public interface AccountMasterRepo extends JpaRepository<AccountMasterEntity, Integer> {
	

}
