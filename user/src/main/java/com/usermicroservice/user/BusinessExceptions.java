package com.usermicroservice.user;

public class BusinessExceptions extends RuntimeException  {
	
		private final String code;
		private final String msg;
		private final Object data;
		
		public BusinessExceptions(String code, String msg, Object data) {
			super(msg);
			this.code = code;
			this.msg = msg;
			this.data = data;
		}
		
		public BusinessExceptions(String code, String msg) {
			super(msg);
			this.code = code;
			this.msg = msg;
			this.data = null;
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
