package com.usermicroservice.user.DTO;

import java.util.Map;

public class ValidationResponse {
	private Map<String, String> validationErrors;

    public ValidationResponse(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public Map<String, String> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(Map<String, String> validationErrors) {
        this.validationErrors = validationErrors;
    }

}
