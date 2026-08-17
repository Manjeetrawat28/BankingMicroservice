package com.bank.notification.DTO;

public class ResponseDTO<T> {
	private String code;
	private String msg;
	private T data;

	public ResponseDTO(String code, String err_msg, T data) {
		super();
		this.code = code;
		this.msg = err_msg;
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getErr_msg() {
		return msg;
	}
	public void setErr_msg(String err_msg) {
		this.msg = err_msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	public ResponseDTO() {
    }
	
	
	
}
