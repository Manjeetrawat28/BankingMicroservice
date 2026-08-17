package com.microservice.transaction.DTO;

public class APIResponse<T> {
	private String ErrorCode;
	private String ErrorMsg;
	
	private T data;

	public APIResponse(String errorCode, String errorMsg, T data) {
		super();
		ErrorCode = errorCode;
		ErrorMsg = errorMsg;
		this.data = data;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "APIResponse [ErrorCode=" + ErrorCode + ", ErrorMsg=" + ErrorMsg + ", data=" + data + "]";
	}
	
	
}
