package com.microservice.account;

public class BusinessError {
	private BusinessError() { }
		public static final String SUCCESS = "00";
		public static final String No_Account_Found = "N1";
		public static final String No_data_in_Request = "U1";
		public static final String SYSTEM_ERROR = "400";
		public static final String Validation_Error = "R1";
		public static final String Accout_Status = "I1";
		public static final String OverDue = "O1";
		public static final String Data_Integration_Violation = "D1";
		
		public static final String Success_MSG = "Success";
		public static final String No_Account_Found_MSG = "No Account Found with Provided Account/UserID";
		public static final String No_Data_in_Request = "Chutiya hai kya bsdk";
		public static final String SYSTEM_ERROR_MSG = "Internal Server Error";
		public static final String Validation_Error_MSG = "Invalid Request";
		public static final String Accout_Status_MSG = "Account is Inactive";
		public static final String OverDue_MSG = "No Funds Available, Account will be OverDrawan";
		public static final String Data_Integration_Violation_MSG = "Duplicate Entry Found";
}
