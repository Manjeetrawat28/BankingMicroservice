package com.microservice.transaction.DTO;

import com.fasterxml.jackson.databind.JsonNode;

public class EndPointResponseDTO {
	private String code;
	private String msg;
	private JsonNode data;
	
	 
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
	public JsonNode getData() {
		return data;
	}
	public void setData(JsonNode data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "EndPointResponseDTO [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	
	
	
}
