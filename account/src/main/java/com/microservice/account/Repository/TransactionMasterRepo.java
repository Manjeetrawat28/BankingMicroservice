package com.microservice.account.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.account.Entity.TransactionMasterEntity;

public interface TransactionMasterRepo extends JpaRepository<TransactionMasterEntity, String> {

}
