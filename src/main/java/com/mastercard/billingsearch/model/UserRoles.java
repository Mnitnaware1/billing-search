package com.mastercard.billingsearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
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
		super();
		this.downloadSummaryCount = downloadSummaryCount;
	}

	
}
