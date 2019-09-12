package com.mastercard.billingsearch.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;
import com.mastercard.billingsearch.model.UserRoles;
import com.mastercard.billingsearch.repository.SummaryReportRepository;

@Service
public class SummaryServiceImpl implements SummaryService {

	@Autowired
	SummaryReportRepository summaryRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<CSVResponse> exportSummaryRecords(int count,CSVRequest csvResponceDto) {///exportSummaryRecords
		return summaryRepository.getSummaryRecords(count,csvResponceDto);
	}
	@Override
	public  List<Map<String, Object>> billingTransactionDetails(String feederType, List<UserRoles> elementMappingDetail,int totalRecords) {
		return summaryRepository.getBillingTransactionDetails(feederType,elementMappingDetail,totalRecords);
	}
	@Override
	public String downloadSummaryCount(String userId) {
		 return summaryRepository.getDownloadSummaryCount(userId);
	}
	@Override
	public String roleName(String userId) {
		// TODO Auto-generated method stub
	  return summaryRepository.getRoleName(userId);
	}
	@Override
	public List<UserRoles> elementMappingDetails(String roleName, String feederType) {
		   return summaryRepository.getElementMappingDetails(roleName,feederType);
		
	}
	

	
}