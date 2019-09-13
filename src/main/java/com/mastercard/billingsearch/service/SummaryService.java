package com.mastercard.billingsearch.service;

import java.util.List;

import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;



public interface SummaryService {
	
	public List<CSVResponse> exportSummaryRecords(String userId, CSVRequest csvRequest);
}
