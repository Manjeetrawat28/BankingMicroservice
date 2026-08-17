package com.microservice.transaction.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.transaction.Entity.TransactionState;

public interface TransactionStateRepo extends JpaRepository<TransactionState, Long> {

}
