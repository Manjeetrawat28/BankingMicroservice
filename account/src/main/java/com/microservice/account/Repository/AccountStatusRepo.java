package com.microservice.account.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservice.account.DTO.AccountAndStatusDTO;
import com.microservice.account.Entity.AccountMasterEntity;

public interface AccountStatusRepo extends JpaRepository<AccountMasterEntity, Integer> {
	

    @Query("SELECT a FROM AccountMasterEntity a WHERE a.account_number = :accountNumber")
    AccountMasterEntity findAccountByAccountNumber(@Param("accountNumber") String accountNumber);
	
	@Query("""
			
			SELECT new com.microservice.account.DTO.AccountAndStatusDTO(
			a.account_number, a.status)
			from AccountMasterEntity a 
			Where (a.account_number= :account_number)"""
			) AccountAndStatusDTO accountStatus(
					@Param("account_number") String account_number);
}
