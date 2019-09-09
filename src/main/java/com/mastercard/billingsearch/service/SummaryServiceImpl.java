package com.mastercard.billingsearch.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;
import com.mastercard.billingsearch.model.UserRoles;
import com.mastercard.billingsearch.repository.SummaryReportRepository;

@Service
public class SummaryServiceImpl implements SummaryService {

	@Autowired
	SummaryReportRepository summaryRepository;
	public List<CSVResponse> exportSummaryRecords(int count,CSVRequest csvResponceDto) {///exportSummaryRecords
		return summaryRepository.getSummaryRecords(count,csvResponceDto);
	}
	@Override
	public  List<Map<String, Object>> billingTransactionDetails(String feederType, UserRoles userRoles) {
		return summaryRepository.getBillingTransactionDetails(feederType,userRoles);
	}

	
}