package com.mastercard.billingsearch.model;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Data
public class UserRoles implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String role;
	private String elements;
	private String asFields;
	private char enable;


	public UserRoles() {
		super();
	}


	
}
