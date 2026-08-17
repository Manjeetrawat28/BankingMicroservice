package com.usermicroservice.userAOP;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class globalLoggingAOP {
	private Logger logger = LogManager.getLogger(globalLoggingAOP.class);
	
	@Around("execution(* com.usermicroservice.user..*(..))")
	public Object log(ProceedingJoinPoint jointPoint) throws Throwable {
		String methodName =  jointPoint.getSignature().toShortString();
		logger.info("Class name: ", methodName);
		
		long startTime = System.currentTimeMillis();

        try {
            Object result = jointPoint.proceed();
            long timeTaken = System.currentTimeMillis() - startTime;

            logger.info("Exiting {} | timetaken={} ms", methodName, timeTaken);
            return result;

        } catch (Exception ex) {
            logger.error("Exception in {}", methodName, ex);
            throw ex;
        }
	}
	
	
}
