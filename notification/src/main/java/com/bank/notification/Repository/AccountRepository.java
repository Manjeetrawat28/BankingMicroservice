package com.bank.notification.Repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.notification.Entity.AccountMaster;

public interface AccountRepository extends JpaRepository<AccountMaster, String> {
	
	@Query(value = """
		    SELECT u.email
		    FROM user_master u
		    JOIN account_master a ON u.user_id = a.user_id
		    WHERE a.account_number = :accountNumber
		    """, nativeQuery = true)
		String findEmaiByAcct(@Param("accountNumber") String accountNumber);
	
}
