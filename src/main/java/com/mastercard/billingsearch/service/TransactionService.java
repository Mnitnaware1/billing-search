package com.mastercard.billingsearch.service;

import java.util.List;
import java.util.Map;

import com.mastercard.billingsearch.model.UserRoles;

public interface TransactionService {
	
	public List<Map<String, Object>> billingTransactionDetails(String feederType, String userId, int totalRecords);
	
	public List<UserRoles> elementMappingDetails(String userId, String feederType);

}
