package com.mastercard.billingsearch.model;

import java.io.Serializable;

public class BillingTransactionDetailResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ime;
	private String functionCode;
	private String trxnType;
	private String issuerICA;
	private String AcuirerICA;
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getFunctionCode() {
		return functionCode;
	}
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	public String getTrxnType() {
		return trxnType;
	}
	public void setTrxnType(String trxnType) {
		this.trxnType = trxnType;
	}
	public String getIssuerICA() {
		return issuerICA;
	}
	public void setIssuerICA(String issuerICA) {
		this.issuerICA = issuerICA;
	}
	public String getAcuirerICA() {
		return AcuirerICA;
	}
	public void setAcuirerICA(String acuirerICA) {
		AcuirerICA = acuirerICA;
	}
	@Override
	public String toString() {
		return "BillingTransactionDetailResponse [ime=" + ime + ", functionCode=" + functionCode + ", trxnType="
				+ trxnType + ", issuerICA=" + issuerICA + ", AcuirerICA=" + AcuirerICA + "]";
	}
	



}
