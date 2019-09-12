package com.mastercard.billingsearch.model;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CSVRequest {
    @NotNull
	private String invoiceDate;
    @NotNull
	private String billEventId;
    @NotNull
	private String invoiceNumber;
    @NotNull
	private String activityICA;
    @NotNull
	private String userType;
    @NotNull
	private String feederType;

	
}
