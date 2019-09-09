package com.mastercard.billingsearch.service;

import java.util.List;
import java.util.Map;

import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;
import com.mastercard.billingsearch.model.UserRoles;



public interface SummaryService {
	public List<CSVResponse> exportSummaryRecords(int count, CSVRequest csvRequest);

	public List<Map<String, Object>> billingTransactionDetails(String feederType, UserRoles userRoles);

}
