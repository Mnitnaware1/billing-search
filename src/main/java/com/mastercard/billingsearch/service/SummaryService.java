package com.mastercard.billingsearch.service;

import java.util.List;
import java.util.Map;

import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;
import com.mastercard.billingsearch.model.UserRoles;



public interface SummaryService {
	public String downloadSummaryCount(String userId);
	
	public List<CSVResponse> exportSummaryRecords(int count, CSVRequest csvRequest);

	public List<Map<String, Object>> billingTransactionDetails(String feederType, List<UserRoles> elementMappingDetails, int totalRecords);
      public String roleName(String userId);

	public List<UserRoles> elementMappingDetails(String roleName, String feederType);
      
 
}
