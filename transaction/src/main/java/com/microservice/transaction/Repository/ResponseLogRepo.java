package com.microservice.transaction.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.transaction.Entity.ResponseLog;

public interface ResponseLogRepo extends JpaRepository<ResponseLog, String> {

}
