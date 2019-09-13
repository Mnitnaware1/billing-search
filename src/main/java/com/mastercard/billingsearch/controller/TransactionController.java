package com.mastercard.billingsearch.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mastercard.billingsearch.config.RootConfiguration;
import com.mastercard.billingsearch.service.TransactionService;
import com.mastercard.billingsearch.utility.ExportCSV;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@RestController
@RequestMapping("/billing/details")
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;

	@Autowired
	private RootConfiguration rootConfiguration;

	/***
	 * @param imeTraceId
	 * @param feederType		 * 
	 * @return billing Transaction Details
	 */
	@GetMapping(value = "{imeTraceId}")
	public ResponseEntity<Object> billingTransactionDetail(@PathVariable("imeTraceId") String imeTraceId,
			@RequestParam("feederType") String feederType, @RequestHeader("userId") String userId)
					throws JsonProcessingException {
		return new ResponseEntity<>(transactionService.billingTransactionDetails(feederType, userId,
				rootConfiguration.getTotalRecords()), HttpStatus.OK);
	}
	
	/***
	 * @param imeTraceId
	 * @param feederType		 
	 * @return csv file
	 */
	@GetMapping(value = "{imeTraceId}/download")
	public ResponseEntity<String> billingDetailsDownload(@PathVariable("imeTraceId") String imeTraceId,
			@RequestParam("feederType") String feederType, @RequestHeader("userId") String userId,HttpServletResponse response)
					throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		List<Map<String, Object>> billingTransactionDetails = transactionService.billingTransactionDetails(feederType, userId,
				rootConfiguration.getTotalRecords());
		ExportCSV.exportCSV(response, billingTransactionDetails, rootConfiguration.getTransactiondetailfile());
		return ResponseEntity.status(HttpStatus.OK).build();

	}
}
