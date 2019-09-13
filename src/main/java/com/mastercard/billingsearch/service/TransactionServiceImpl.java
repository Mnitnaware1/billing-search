package com.mastercard.billingsearch.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mastercard.billingsearch.model.UserRoles;
import com.mastercard.billingsearch.repository.TransactionRepository;
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public List<Map<String, Object>> billingTransactionDetails(String feederType, List<UserRoles> elementMappingDetail,
			int totalRecords) {
		return transactionRepository.getBillingTransactionDetails(feederType, elementMappingDetail, totalRecords);
	}

	@Override
	public List<UserRoles> elementMappingDetails(String userId, String feederType) {

		return transactionRepository.getElementMappingDetails(getRoleName(userId), feederType);

	}

	public String getRoleName(String userId) {
		String sqlQueryToFetchRoleName = "SELECT role_name FROM USER_ROLES where user_id=?";
		return jdbcTemplate.queryForObject(sqlQueryToFetchRoleName, new Object[] { userId }, String.class);
	}

}
