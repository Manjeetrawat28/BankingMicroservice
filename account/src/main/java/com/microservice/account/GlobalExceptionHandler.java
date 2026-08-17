package com.microservice.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.microservice.account.DTO.ValidationResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<APIResponse<?>> handleBusinessException(BusinessException ex){
		APIResponse<?> resp = new APIResponse(ex.getCode(), ex.getMsg(), ex.getData());
		return ResponseEntity.ok(resp);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<APIResponse<Map>> handleArugmentNotValid(MethodArgumentNotValidException ex){
		Map<String, String> validationMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> 
		validationMap.put(error.getField(), error.getDefaultMessage()));
		
		ValidationResponse error_map = new ValidationResponse(validationMap);
		
		APIResponse<Map> resp =  new APIResponse(BusinessError.Validation_Error, BusinessError.Validation_Error_MSG, error_map);
		return ResponseEntity.badRequest().body(resp);
	} 
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<APIResponse<?>> handleDataIntegrityException(DataIntegrityViolationException ex){
		Map<String, Object> mp = new HashMap<>();
		mp.put("Details", ex.getMostSpecificCause().getMessage() );
		
		APIResponse<?> res = new APIResponse(BusinessError.Data_Integration_Violation, BusinessError.Data_Integration_Violation_MSG,mp);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<?>> handleException(Exception ex){
		APIResponse<?> resp  = new APIResponse(BusinessError.SYSTEM_ERROR, BusinessError.SYSTEM_ERROR_MSG ,null);
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
	}
	
	
}
