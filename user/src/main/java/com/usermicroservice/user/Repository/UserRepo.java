package com.usermicroservice.user.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermicroservice.user.Entity.userMasterEntity;

@Repository
public interface UserRepo extends JpaRepository<userMasterEntity, Integer> {
	Optional<userMasterEntity> findByEmail(String email);
	//Optional<userMasterEntity>findBy(String account_number);
	Optional<userMasterEntity>findById(Integer user_id);
}
