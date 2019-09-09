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
	
	public String getRoleApiUrl() {
		return roleApiUrl;
	}
	
	public void setRoleApiUrl(String roleApiUrl) {
		this.roleApiUrl = roleApiUrl;
	}
	public String getSchemaFile() {
		return schemaFile;
	}
	public void setSchemaFile(String schemaFile) {
		this.schemaFile = schemaFile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getBillingSummaryFileName() {
		return billingSummaryFileName;
	}

	public void setBillingSummaryFileName(String billingSummaryFileName) {
		this.billingSummaryFileName = billingSummaryFileName;
	}
	
	
}
