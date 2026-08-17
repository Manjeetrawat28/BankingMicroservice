package com.microservice.transaction.AOP;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.microservice.transaction.BusinessRules.BusinessException;



@Component
@Aspect
public class GlobalLoggingAOP {
	private Logger logger = LogManager.getLogger(GlobalLoggingAOP.class);
	
	@Around("execution(* com.microservice.transaction.Service..*(..))")
	public Object log(ProceedingJoinPoint jointPoint) throws Throwable {
		String methodName =  jointPoint.getSignature().toShortString();
		logger.info("Class name: {}", methodName);
		
		long startTime = System.currentTimeMillis();

        try {
            Object result = jointPoint.proceed();
            long timeTaken = System.currentTimeMillis() - startTime;

            logger.info("Exiting {} | timetaken={} ms", methodName, timeTaken);
            return result;

        } catch (Exception ex) {
        	if (ex instanceof BusinessException) {
                if (logger.isDebugEnabled() || logger.isTraceEnabled()) {
                    logger.debug("Exception in {}", methodName, ex);
                }
            } else {
                logger.error("Exception in {}", methodName, ex);
            }
            throw ex;
        }
	}

}
