package com.microservice.transaction.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.transaction.Entity.RequestLog;

public interface RequestLogRepo extends JpaRepository<RequestLog, String> {

}
