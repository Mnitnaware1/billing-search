package com.mastercard.billingsearch.test;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.billingsearch.controller.BillingSummaryController;
import com.mastercard.billingsearch.controller.RootConfiguration;
import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;
import com.mastercard.billingsearch.model.UserRoles;
import com.mastercard.billingsearch.service.SummaryService;
import com.mastercard.billingsearch.utility.JsonSchemaValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = BillingSummaryController.class, secure = false)
public class TestBillingSummaryController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RootConfiguration rootConfiguration;

	@MockBean
	private SummaryService summaryService;

	@MockBean
	JsonSchemaValidator jsonSchemaValidator;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private ObjectMapper objectMapper;

	@Mock
	CSVRequest csvRequest;
 


	@Test
	public void testBillingSummaryDownload() throws Exception {
		String statusMesssage = "success";
		// power mock static method class
		Mockito.when(jsonSchemaValidator.validateJsonData(Mockito.anyString(), Mockito.anyString()))
		.thenReturn(statusMesssage);

		//System.out.println("root config "+rootConfiguration.getRoleApiUrl());
		Map<String, String> urlParams = new HashMap<>(); UriComponentsBuilder builder
		= UriComponentsBuilder.fromUriString("http://localhost:8080/roleApi/exportableRecords")
		.queryParam("userId", 1)
		.queryParam("feederType", "AUTH");

		URI uri = builder.buildAndExpand(urlParams).toUri();

		
		UserRoles userDetails = new UserRoles(2);
		Mockito.when(restTemplate.getForObject(uri, UserRoles.class)).thenReturn(userDetails);
		
		//CSVRequest csvRequest=new CSVRequest();
		csvRequest.setInvoiceDate("2019-08-29");
		csvRequest.setActivityICA("ICA1");
		csvRequest.setInvoiceNumber("INVNUM1");
		csvRequest.setBillEventId("BILLEVNTID1");
		
		Mockito.when(objectMapper.readValue(" {\"invoiceDate\": \"invoiceDate\",  \"billEventId\": \"billEventId\", \"invoiceNumber\": \"invoiceNumber\",\"activityICA\": \"activityICA\"}",CSVRequest.class)).thenReturn(csvRequest);
		
		List<CSVResponse> summaryRecords=new ArrayList<>();
		CSVResponse csvResponse=new CSVResponse();
		csvResponse.setInvoiceDate("2019-08-29");
		csvResponse.setActivityIca("ICA1");
		csvResponse.setBillEventId("BILLEVNTID1");
		csvResponse.setInvNum("INVNUM1");
		summaryRecords.add(csvResponse);

		Mockito.when(summaryService.exportSummaryRecords(Mockito.anyInt(),Mockito.any(CSVRequest.class))).thenReturn(summaryRecords);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/download").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
	}
	
	@Test
	public void testBillingTransactionDetail() throws Exception {
		
		Map<String, String> urlParams = new HashMap<>(); UriComponentsBuilder builder
		= UriComponentsBuilder.fromUriString("http://localhost:8080/roleApi/exportableRecords")
		.queryParam("userId", 1);

		URI uri = builder.buildAndExpand(urlParams).toUri();

		List<Map<String, Object>> list=new ArrayList<>();
		UserRoles userDetails = new UserRoles(2);
		Mockito.when(restTemplate.getForObject(uri, UserRoles.class)).thenReturn(userDetails);
		Mockito.when(summaryService.billingTransactionDetails(Mockito.anyString(),Mockito.any(UserRoles.class),Mockito.anyInt())).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/details").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());

	}

}
