package com.mastercard.billingsearch.model;

public class CSVResponse {
	private String summaryTraceId;
	private String billEventId;
	private String invoiceDate;
	private String invNum;
	private String activityIca;
	private String feederId;
	public String getSummaryTraceId() {
		return summaryTraceId;
	}
	public void setSummaryTraceId(String summaryTraceId) {
		this.summaryTraceId = summaryTraceId;
	}
	public String getBillEventId() {
		return billEventId;
	}
	public void setBillEventId(String billEventId) {
		this.billEventId = billEventId;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getInvNum() {
		return invNum;
	}
	public void setInvNum(String invNum) {
		this.invNum = invNum;
	}
	
	public String getActivityIca() {
		return activityIca;
	}
	public void setActivityIca(String activityIca) {
		this.activityIca = activityIca;
	}
	public String getFeederId() {
		return feederId;
	}
	public void setFeederId(String feederId) {
		this.feederId = feederId;
	}
	@Override
	public String toString() {
		return "CSVResponse [summaryTraceId=" + summaryTraceId + ", billEventId=" + billEventId + ", invoiceDate="
				+ invoiceDate + ", invNum=" + invNum + ", activityIca=" + activityIca + ", feederId=" + feederId + "]";
	}
	
}
