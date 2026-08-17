package com.usermicroservice.user.DTO;

public class EndUserResponse<T> {
	private String code;
	private String msg;
	private T data;
	
	
	
	
	public EndUserResponse(String  code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "EndUserResponse [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
	
}
