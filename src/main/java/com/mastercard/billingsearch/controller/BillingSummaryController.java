package com.mastercard.billingsearch.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;
import com.mastercard.billingsearch.model.UserRoles;
import com.mastercard.billingsearch.service.SummaryService;
import com.mastercard.billingsearch.utility.Constants;
import com.mastercard.billingsearch.utility.ExportCSV;
import com.mastercard.billingsearch.utility.JsonSchemaValidator;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

@RestController
@RequestMapping("/billing/summary")
public class BillingSummaryController {

	@Autowired
	private RestTemplate restTemplate;

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
	public ResponseEntity<Object> billingSummaryDownload(@RequestBody String summary,
			@RequestHeader("userId") String userId, HttpServletResponse response) throws IOException,
			ProcessingException, URISyntaxException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		JsonSchemaValidator jsonSchemaValidator = new JsonSchemaValidator();
		String validateJsonData = jsonSchemaValidator.validateJsonData(rootConfiguration.getSchemaFile(), summary);

		if (validateJsonData.contains(Constants.SUCCESS_STR)) {
			ObjectMapper objectMapper = new ObjectMapper();
			CSVRequest csvRequest = objectMapper.readValue(summary, CSVRequest.class);

			URI requestURI = buildURLWithParameters(userId, csvRequest.getFeederType());

			UserRoles userRoles = restTemplate.getForObject(requestURI, UserRoles.class);

			List<CSVResponse> summaryReport = summaryService.exportSummaryRecords(userRoles.getDownloadSummaryCount(),
					csvRequest);

			ExportCSV.export(response, summaryReport, rootConfiguration.getBillingSummaryFileName());
		}

		return ResponseEntity.status(HttpStatus.OK).build();

	}
	// role -BILL_OPS
	// feederType AUTH

	@GetMapping(value = "details/{imeTraceId}")
	public List<Map<String, Object>> billingTransactionDetail(@PathVariable("imeTraceId") String imeTraceId,
			@RequestParam("feederType") String feederType, @RequestHeader("userId") String userId)
			throws JsonProcessingException {

		URI requestURI = buildURLWithParameters(userId, feederType);

		UserRoles userRoles = restTemplate.getForObject(requestURI, UserRoles.class);
		// feederType used to form table name dynamically
		return summaryService.billingTransactionDetails(feederType, userRoles);
	}

	// this method builds uri with the given parameters
	private URI buildURLWithParameters(String userId, String feederType) {

		Map<String, String> urlParams = new HashMap<>();
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(rootConfiguration.getRoleApiUrl())
				.queryParam("userId", userId).queryParam("feederType", feederType);
		return builder.buildAndExpand(urlParams).toUri();
	}
}
