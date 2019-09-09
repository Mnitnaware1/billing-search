package com.mastercard.billingsearch.model;

public class CSVRequest {

	private String invoiceDate;
	private String billEventId;
	private String invoiceNumber;
	private String activityICA;
	private String userType;
	private String feederType;
	
	public CSVRequest() {
		super();
	}

	public CSVRequest(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getBillEventId() {
		return billEventId;
	}

	public void setBillEventId(String billEventId) {
		this.billEventId = billEventId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getActivityICA() {
		return activityICA;
	}

	public void setActivityICA(String activityICA) {
		this.activityICA = activityICA;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFeederType() {
		return feederType;
	}

	public void setFeederType(String feederType) {
		this.feederType = feederType;
	}

	@Override
	public String toString() {
		return "CSVRequest [invoiceDate=" + invoiceDate + ", billEventId=" + billEventId + ", invoiceNumber="
				+ invoiceNumber + ", activityICA=" + activityICA + ", userType=" + userType + ", feederType="
				+ feederType + "]";
	}


	
 
}
