package com.mastercard.billingsearch.model;

import java.util.Arrays;

public class UserRoles {
	private String roleName;
	private String[] detailFields;
	private String[] asFields;
	private int downloadSummaryCount;
	private String downloadDetailCount;
	private String summaryResponseCount;
	private String detailResponseCount;

	public UserRoles() {
		super();
	}

	public UserRoles(int downloadSummaryCount) {

	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String[] getDetailFields() {
		return detailFields;
	}

	public void setDetailFields(String[] detailFields) {
		this.detailFields = detailFields;
	}

	public String[] getAsFields() {
		return asFields;
	}

	public void setAsFields(String[] asFields) {
		this.asFields = asFields;
	}

	public int getDownloadSummaryCount() {
		return downloadSummaryCount;
	}

	public void setDownloadSummaryCount(int downloadSummaryCount) {
		this.downloadSummaryCount = downloadSummaryCount;
	}

	public String getDownloadDetailCount() {
		return downloadDetailCount;
	}

	public void setDownloadDetailCount(String downloadDetailCount) {
		this.downloadDetailCount = downloadDetailCount;
	}

	public String getSummaryResponseCount() {
		return summaryResponseCount;
	}

	public void setSummaryResponseCount(String summaryResponseCount) {
		this.summaryResponseCount = summaryResponseCount;
	}

	public String getDetailResponseCount() {
		return detailResponseCount;
	}

	public void setDetailResponseCount(String detailResponseCount) {
		this.detailResponseCount = detailResponseCount;
	}

	@Override
	public String toString() {
		return "UserDetails [roleName=" + roleName + ", detailFields=" + Arrays.toString(detailFields) + ", asFields="
				+ Arrays.toString(asFields) + ", downloadSummaryCount=" + downloadSummaryCount
				+ ", downloadDetailCount=" + downloadDetailCount + ", summaryResponseCount=" + summaryResponseCount
				+ ", detailResponseCount=" + detailResponseCount + "]";
	}

}
