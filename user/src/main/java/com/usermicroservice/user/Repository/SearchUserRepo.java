package com.usermicroservice.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.usermicroservice.user.DTO.UserDetailsDTO;
import com.usermicroservice.user.Entity.userMasterEntity;
@Repository
public interface SearchUserRepo extends JpaRepository<userMasterEntity, Integer> {
	
	@Query("""
			Select new com.usermicroservice.user.DTO.UserDetailsDTO(
			u.user_id,u.first_name, u.last_name, u.email, a.account_number, a.amount, a.status )
			from  userMasterEntity u join AccountMasterEntity a ON a.user_id = u.user_id
			where (:email IS NULL or u.email = :email) and (:user_id IS NULL or u.user_id = :user_id)
			and (:account_number IS NULL or a.account_number = :account_number)
			""") UserDetailsDTO findUserDetails(
					@Param("email") String email,
					@Param("user_id") Integer user_id,
					@Param("account_number") String account_number );

}
