package com.mastercard.billingsearch.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;
import com.mastercard.billingsearch.model.UserRoles;
import com.mastercard.billingsearch.service.SummaryService;
import com.mastercard.billingsearch.utility.ExportCSV;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@RestController
@RequestMapping("/billing/summary")
public class BillingSummaryController {

	@Autowired
	SummaryService summaryService;

	@Autowired
	private RootConfiguration rootConfiguration;

	/***
	 * validate request body using schema validator ,call to role API to get
	 * exportable records
	 * 
	 * @return billing summary downloaded csv
	 */

	@PostMapping(value = "/download")
	public ResponseEntity<Object> billingSummaryDownload(@RequestBody CSVRequest csvRequest,
			@RequestHeader("userId") String userId, HttpServletResponse response) throws IOException,
			ProcessingException, URISyntaxException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		// make a call to role_mapping table to get download count
		String downloadSummaryCount = summaryService.downloadSummaryCount(userId);
		List<CSVResponse> summaryReport = summaryService.exportSummaryRecords(Integer.parseInt(downloadSummaryCount),
				csvRequest);

		ExportCSV.export(response, summaryReport, rootConfiguration.getBillingSummaryFileName());

		return ResponseEntity.status(HttpStatus.OK).build();

	}

	// role -BILL_OPS
	// feederType AUTH
	/***
	 * call to role API to get UserRoles
	 * 
	 * @return billing Transaction Details
	 */
	@GetMapping(value = "details/{imeTraceId}")
	public List<Map<String, Object>> billingTransactionDetail(@PathVariable("imeTraceId") String imeTraceId,
			@RequestParam("feederType") String feederType, @RequestHeader("userId") String userId)
			throws JsonProcessingException {

		String roleName = summaryService.roleName(userId);
		List<UserRoles> elementMappingDetails = summaryService.elementMappingDetails(roleName,feederType);
		return summaryService.billingTransactionDetails(feederType, elementMappingDetails,
				rootConfiguration.getTotalRecords());

	}

	@GetMapping(value = "details/{imeTraceId}/download")
	public ResponseEntity<Object> billingDetailsDownload(@PathVariable("imeTraceId") String imeTraceId,
			@RequestParam("feederType") String feederType, @RequestHeader("userId") String userId,HttpServletResponse response)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {
		String roleName = summaryService.roleName(userId);
		List<UserRoles> elementMappingDetails = summaryService.elementMappingDetails(roleName,feederType);
		 List<Map<String, Object>> billingTransactionDetails = summaryService.billingTransactionDetails(feederType, elementMappingDetails,
				rootConfiguration.getTotalRecords());
		ExportCSV.exportCSV(response, billingTransactionDetails, rootConfiguration.getTransactiondetailfile());
		return ResponseEntity.status(HttpStatus.OK).build();

	}
}
