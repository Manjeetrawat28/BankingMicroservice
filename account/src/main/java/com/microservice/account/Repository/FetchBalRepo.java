package com.microservice.account.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.microservice.account.DTO.BalEnqResp;
import com.microservice.account.Entity.AccountMasterEntity;

@Repository
public interface FetchBalRepo extends JpaRepository<AccountMasterEntity, Integer> {
	
	@Query("""
	        SELECT new com.microservice.account.DTO.BalEnqResp(
	            a.user_id, a.account_number, a.amount
	        )
	        FROM AccountMasterEntity a
	        WHERE (:user_id IS NULL OR a.user_id = :user_id)
	        AND (:account_number IS NULL OR a.account_number = :account_number)
	    """)
	    
	    BalEnqResp FetchBal(
	        @Param("user_id") Integer userId,
	        @Param("account_number") String accountNumber
	    );

}
