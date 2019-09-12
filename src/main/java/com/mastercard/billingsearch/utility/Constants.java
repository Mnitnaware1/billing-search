package com.mastercard.billingsearch.utility;


public class Constants {
	
	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String SUCCESS_STR = "success";
	public static final String BILLING_EVENT_DETAIL = "_BILLING_EVENT_DETAIL EVENT";
	public static final String TRANSACTION_DETAIL = "_TRANSACTION_DETAIL TRANSACTION";
	public static final String COMMA = ",";
	public static final String AS = " AS ";
	 //Exception constants
    public static final String RECORD_NOT_FOUND_MSG = "No summary records/data found with this details : ";
    public static final String VALIDATION_FAILED = "Validation Failed";
    public static final String SERVER_ERROR = "Server Error";
    public static final String CHECK_LOGS_ROOT_CAUSE = "Check logs for root cause";
    public static final String RECORD_NOT_FOUND = "Record Not Found";
    public static final String MESSAGE = "message";
    public static final String ERRORS = "errors";
    public static final String TIMESTAMP = "timestamp";



}
