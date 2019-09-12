package com.mastercard.billingsearch.repository;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;
import com.mastercard.billingsearch.model.UserRoles;
import com.mastercard.billingsearch.utility.Constants;

@Repository
public class SummaryReportRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * this method is to fetch the summary records
	 * 
	 * @return summary records
	 **/
	public List<CSVResponse> getSummaryRecords(int count, CSVRequest csvRequest) {
		String sqlQuery = "select * from charge_detail cd , charge_transaction_trace ct where cd.summary_trace_id=ct.billing_summary_trace_id and\r\n"
				+ " cd.invoice_Date = ? AND cd.inv_num = ? AND cd.activity_ica =? AND ct.bill_event_id = ?";
		jdbcTemplate.setMaxRows(count);
		return jdbcTemplate.query(sqlQuery,
				new Object[] { csvRequest.getInvoiceDate(), csvRequest.getInvoiceNumber(), csvRequest.getActivityICA(),
						csvRequest.getBillEventId() },
				new BeanPropertyRowMapper<CSVResponse>(CSVResponse.class));
	}

	public List<Map<String, Object>> getBillingTransactionDetails(String feederType, List<UserRoles> elementMappingDetail,
			int totalRecords) {
		// TODO if detailFields and asFields are nulls
		Object[] detailFields = elementMappingDetail.stream().map(e->e.getElements()).toArray();
		Object[] asFields = elementMappingDetail.stream().map(e->e.getAsFields()).toArray();
		String buildAsQuery = buildAsQuery(detailFields, asFields);
		String buildTableNamesUingFeederType = buildTableNamesUingFeederType(feederType);
		String sqlQuery = "SELECT " + buildAsQuery + " FROM " + buildTableNamesUingFeederType
				+ " WHERE EVENT.IME_TRACE_ID = TRANSACTION.IME_TRACE_ID";
		jdbcTemplate.setMaxRows(totalRecords);
		return jdbcTemplate.queryForList(sqlQuery);
	}

	// auth
	private String buildTableNamesUingFeederType(String feederType) {
		StringBuilder feederTypeStr = new StringBuilder(feederType);// is it pos to add table type in event detail
		feederTypeStr.append(Constants.BILLING_EVENT_DETAIL).append(Constants.COMMA)
				.append(feederType + Constants.TRANSACTION_DETAIL);
		return feederTypeStr.toString();
	}

	private String buildAsQuery(Object[] detailFields, Object[] asFields) {
		StringBuilder stringBuilder = new StringBuilder();

		if (detailFields.length == asFields.length) {
			for (int i = 0; i < asFields.length; i++) {
				Object asField =  asFields[i];
				Object detailField =  detailFields[i];
				stringBuilder.append(detailField).append(Constants.AS).append(asField);
				if (i != asFields.length - 1) {
					stringBuilder.append(Constants.COMMA);
				}
			}
			return stringBuilder.toString();
		}

		return "*";

	}
	
	public String getDownloadSummaryCount(String userId){
		String sqlQueryToFetchRoleName="SELECT download_summary_count FROM role_mapping where ROLE_NAME IN (SELECT ROLE_NAME FROM user_roles where user_id=?)";
		 return jdbcTemplate.queryForObject(
	                sqlQueryToFetchRoleName,
	                new Object[]{userId},
	                String.class
	        );
		
	}

	public String getRoleName(String userId) {
		String sqlQueryToFetchRoleName="SELECT role_name FROM USER_ROLES where user_id=?";
		 return jdbcTemplate.queryForObject(
	                sqlQueryToFetchRoleName,
	                new Object[]{userId},
	                String.class
	        );
	}

	public List<UserRoles> getElementMappingDetails(String roleName, String feederType) {
		String sqlQueryToFetchRoleName="SELECT ELEMENTS,AS_FIELDS FROM ELEMENT_MAPPING WHERE role=? AND FEEDER_TYPE=? AND ENABLE='Y'";
		
		 return jdbcTemplate.query(
	                sqlQueryToFetchRoleName,
	                new Object[]{roleName,feederType},
	                new BeanPropertyRowMapper<UserRoles>(UserRoles.class)
	        ); 
	}

}
