package com.bank.notification.DTO;

public class ResponseBody {
    
    private String tnxID;
    private String message;

    public ResponseBody(){}
    public ResponseBody(String tnxID, String message) {
        this.tnxID = tnxID;
        this.message = message;
    }
    public String getTnxID() {
        return tnxID;
    }
    public void setTnxID(String tnxID) {
        this.tnxID = tnxID;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
}
