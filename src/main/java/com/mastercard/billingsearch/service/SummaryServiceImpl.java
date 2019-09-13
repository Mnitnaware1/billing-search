package com.mastercard.billingsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;
import com.mastercard.billingsearch.repository.SummaryReportRepository;

@Service
public class SummaryServiceImpl implements SummaryService {

	@Autowired
	SummaryReportRepository summaryRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<CSVResponse> exportSummaryRecords(String userId, CSVRequest csvRequest) {
		return summaryRepository.getSummaryRecords(Integer.parseInt(getDownloadSummaryCount(userId)), csvRequest);
	}
	public String getDownloadSummaryCount(String userId){
		String sqlQueryToFetchRoleName="SELECT download_summary_count FROM role_mapping where ROLE_NAME IN (SELECT ROLE_NAME FROM user_roles where user_id=?)";
		 return jdbcTemplate.queryForObject(
	                sqlQueryToFetchRoleName,
	                new Object[]{userId},
	                String.class
	        );
		
	}
}