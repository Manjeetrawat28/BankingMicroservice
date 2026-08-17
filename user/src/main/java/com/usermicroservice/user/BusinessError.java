package com.usermicroservice.user;

public class BusinessError {
	private BusinessError() {}

    public static final String SUCCESS = "00";
    public static final String DUPLICATE_EMAIL = "U1";
    public static final String Validation_Failed = "V1";
    public static final String No_data_in_request = "404";
    public static final String No_User_Found = "N1";
    public static final String SYSTEM_ERROR = "444";

    public static final String USER_CREATED_MSG = "User created";
    public static final String DUPLICATE_EMAIL_MSG = "Email already exists";
    public static final String Validation_failed_MSG = "Please check the Input Deatils";
    public static final String SYSTEM_ERROR_MSG = "System Error. Please try again later";
    public static final String No_data_in_request_MSG = "Please Fill any one Field";
    public static final String No_User_Found_MSG = "No User Found With Provided Details";
    public static final String User_Found_MSG = "User Found";
    
}
