package com.microservice.transaction.BusinessRules;

import java.util.HashMap;
import java.util.Map;

public class ValidationResponse {
	private Map<String, String> validationMap;

	public ValidationResponse(Map<String, String> validationMap) {
		super();
		this.validationMap = validationMap;
	}

	public Map<String, String> getValidationMap() {
		return validationMap;
	}

	public void setValidationMap(Map<String, String> validationMap) {
		this.validationMap = validationMap;
	}
	
	
}
