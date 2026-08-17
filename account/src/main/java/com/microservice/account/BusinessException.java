package com.microservice.account;

public class BusinessException extends RuntimeException {
	
	private final String code;
	private final String msg;
	private final Object data;
	public BusinessException(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public Object getData() {
		return data;
	}
	
	

}
