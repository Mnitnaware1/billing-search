package com.mastercard.billingsearch.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorDetails {
	private String message;
	private String errors;
	private Date timestamp;

	public ErrorDetails(String message, String errors, Date timestamp) {
		super();
		this.timestamp = timestamp;
		this.errors = errors;
		this.message = message;
	}
}
