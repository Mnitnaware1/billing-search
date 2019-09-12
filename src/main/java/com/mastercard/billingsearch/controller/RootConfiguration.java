package com.mastercard.billingsearch.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class RootConfiguration {
	
	@Value("${url.exportablerecords}")
	private String roleApiUrl;
	
	@Value("${json.validator.file}")
	private String schemaFile;
	
	@Value("${message}")
	private String message;
	
	@Value("${billingsummaryfile}")
	private String billingSummaryFileName;
	
	@Value("${totalrecords}")
	private int totalRecords;
	
	@Value("${transactiondetailfile}")
	private String transactiondetailfile;

	public String getRoleApiUrl() {
		return roleApiUrl;
	}


	public String getSchemaFile() {
		return schemaFile;
	}


	public String getMessage() {
		return message;
	}

	public String getBillingSummaryFileName() {
		return billingSummaryFileName;
	}

	public int getTotalRecords() {
		return totalRecords;
	}


	public String getTransactiondetailfile() {
		return transactiondetailfile;
	}
	
	
}
