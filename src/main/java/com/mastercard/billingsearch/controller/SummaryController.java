package com.mastercard.billingsearch.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.mastercard.billingsearch.config.RootConfiguration;
import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;
import com.mastercard.billingsearch.service.SummaryService;
import com.mastercard.billingsearch.utility.ExportCSV;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@RestController
@RequestMapping("/billing/summary")
public class SummaryController {

	@Autowired
	SummaryService summaryService;
	
	@Autowired
	private RootConfiguration rootConfiguration;

	/***
	 * @param CSVRequest
	 * @return csv file
	 */

	@PostMapping(value = "/download")
	public ResponseEntity<Object> billingSummaryDownload(@RequestBody CSVRequest csvRequest,
			@RequestHeader("userId") String userId, HttpServletResponse response) throws IOException,
			ProcessingException, URISyntaxException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		List<CSVResponse> summaryReport = summaryService.exportSummaryRecords(userId,
				csvRequest);

		ExportCSV.export(response, summaryReport, rootConfiguration.getBillingSummaryFileName());

		return ResponseEntity.status(HttpStatus.OK).build();

	}

}
