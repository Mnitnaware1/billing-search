package com.mastercard.billingsearch.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;

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
		String sqlQuery = "SELECT * FROM charge_detail cd , charge_transaction_trace ct WHERE cd.summary_trace_id=ct.billing_summary_trace_id and\r\n"
				+ " cd.invoice_Date = ? AND cd.inv_num = ? AND cd.activity_ica =? AND ct.bill_event_id = ?";
		jdbcTemplate.setMaxRows(count);
		return jdbcTemplate.query(sqlQuery,
				new Object[] { csvRequest.getInvoiceDate(), csvRequest.getInvoiceNumber(), csvRequest.getActivityICA(),
						csvRequest.getBillEventId() },
				new BeanPropertyRowMapper<CSVResponse>(CSVResponse.class));
	}
}
