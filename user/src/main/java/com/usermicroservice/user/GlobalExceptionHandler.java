package com.usermicroservice.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.usermicroservice.user.DTO.EndUserResponse;
import com.usermicroservice.user.DTO.ValidationResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(BusinessExceptions.class)
	public ResponseEntity<EndUserResponse<?>> handleBusinessException(BusinessExceptions ex){
		EndUserResponse<?> resp = new EndUserResponse<>(ex.getCode(), ex.getMessage(), ex.getData());
		return ResponseEntity.ok(resp);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<EndUserResponse<?>> handleDuplicateFromDB(
            DataIntegrityViolationException ex) {

        EndUserResponse<?> response =
                new EndUserResponse<>(
                        BusinessError.DUPLICATE_EMAIL,
                        BusinessError.DUPLICATE_EMAIL_MSG,
                        null
                );

        return ResponseEntity.badRequest().body(response);
    }
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<EndUserResponse<?>> handleValidationExceptions( MethodArgumentNotValidException ex){
		Map<String, String> validMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->
		validMap.put(error.getField(), error.getDefaultMessage()));
		
		ValidationResponse error_map =  new ValidationResponse(validMap);
		
		EndUserResponse<?> resp = new EndUserResponse<>(
				BusinessError.Validation_Failed,
				BusinessError.Validation_failed_MSG,
				error_map
				
				);
		return ResponseEntity.badRequest().body(resp);
	}
	
	
	
	 @ExceptionHandler(Exception.class)
	 public ResponseEntity<EndUserResponse<?>> handleSystemException(
	            Exception ex) {

	        EndUserResponse<?> response =
	                new EndUserResponse<>(
	                        BusinessError.SYSTEM_ERROR,
	                        BusinessError.SYSTEM_ERROR_MSG,
	                        null
	                );

	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(response);
	    }

}
