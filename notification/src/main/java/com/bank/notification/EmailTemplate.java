package com.bank.notification;
import java.time.LocalDateTime;
import java.math.BigDecimal;

public class EmailTemplate {
	
	private EmailTemplate() { }
	
	public static String DebitEmail(String account, BigDecimal amount, String txnID) { 
	    int n = account.length();

	    String msg = "Dear Customer,\n"
	            + "Thank You for Banking with us.\n"
	            + "Your A/c no. XXXXX" + account.substring(n-4, n)
	            + " has been debited with INR " + amount + " Transaction ID: "
				+ txnID
	            + " on " + LocalDateTime.now();

	    return msg;
	}
	
	public static String CreditEmail(String account, BigDecimal amount, String txnID) { 
	    int n = account.length();

	    String msg = "Dear Customer,\n"
	            + "Thank You for Banking with us.\n"
	            + "Your A/c no. XXXXX" + account.substring(n-4, n)
	            + " has been credited with INR " + amount + " Transaction ID: "
				+ txnID
	            + " on " + LocalDateTime.now();

	    return msg;
	}
	
	

}
