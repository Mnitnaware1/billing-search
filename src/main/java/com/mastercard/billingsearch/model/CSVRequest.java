package com.mastercard.billingsearch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CSVRequest {

	private String invoiceDate;
	private String billEventId;
	private String invoiceNumber;
	private String activityICA;
	private String userType;
	private String feederType;

	
}
