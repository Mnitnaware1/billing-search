package com.mastercard.billingsearch.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.billingsearch.controller.BillingSummaryController;
import com.mastercard.billingsearch.controller.RootConfiguration;
import com.mastercard.billingsearch.model.CSVRequest;
import com.mastercard.billingsearch.model.CSVResponse;
import com.mastercard.billingsearch.model.UserRoles;
import com.mastercard.billingsearch.service.SummaryService;
import com.mastercard.billingsearch.utility.JsonSchemaValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = BillingSummaryController.class)
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
	

	   @MockBean
	   private BillingSummaryController billingSummaryController;
	 	

	//https://stackoverflow.com/questions/45825955/using-spring-mockmvc-to-test-optional-path-variables
	@Test
	public void testBillingSummaryDownload() throws Exception {

		Mockito.when(summaryService.downloadSummaryCount(Mockito.anyString())).thenReturn(Mockito.anyString());
		// CSVRequest csvRequest=new CSVRequest();
		csvRequest.setInvoiceDate("2019-08-29");
		csvRequest.setActivityICA("ICA1");
		csvRequest.setInvoiceNumber("INVNUM1");
		csvRequest.setBillEventId("BILLEVNTID1");

		List<CSVResponse> summaryRecords = new ArrayList<>();
		CSVResponse csvResponse = new CSVResponse();
		csvResponse.setInvoiceDate("2019-08-29");
		csvResponse.setActivityIca("ICA1");
		csvResponse.setBillEventId("BILLEVNTID1");
		csvResponse.setInvNum("INVNUM1");
		summaryRecords.add(csvResponse);

		Mockito.when(summaryService.exportSummaryRecords(Mockito.anyInt(), Mockito.any(CSVRequest.class)))
				.thenReturn(summaryRecords);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/download").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.err.println(result.getResponse().getContentAsString());
	}

	@Test
	public void testBillingTransactionDetail() throws Exception {

		Mockito.when(summaryService.roleName(Mockito.anyString())).thenReturn(Mockito.anyString());

		List<UserRoles> elementMappingDetails=new ArrayList<>();
		
		UserRoles userRoles=new UserRoles();
		userRoles.setRole("BILL_DEV");
		userRoles.setElements("TRANSACTION.MTI_FUNC_CD");
		userRoles.setAsFields("mtiFuncCode");
		userRoles.setEnable('Y');
		elementMappingDetails.add(userRoles);

		Mockito.when(summaryService.elementMappingDetails(Mockito.anyString(), "IME1")).thenReturn(elementMappingDetails);
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		Mockito.when(summaryService.billingTransactionDetails(Mockito.anyString(), Mockito.anyList(),
				Mockito.anyInt())).thenReturn(list);
		//RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/details/{ime}").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(put("/billing/summary/details/{imeTraceId}","IME1")).andReturn();
		System.out.println(result.getResponse());

	}
	
	
	@Test
	public void testBillingDetailsDownload() throws Exception {

		Mockito.when(summaryService.roleName(Mockito.anyString())).thenReturn(Mockito.anyString());

		List<UserRoles> elementMappingDetails=new ArrayList<>();
		UserRoles userRoles=new UserRoles();
		userRoles.setRole("BILL_DEV");
		userRoles.setElements("TRANSACTION.MTI_FUNC_CD");
		userRoles.setAsFields("mtiFuncCode");
		userRoles.setEnable('Y');
		elementMappingDetails.add(userRoles);

		Mockito.when(summaryService.elementMappingDetails(Mockito.anyString(), "IME1")).thenReturn(elementMappingDetails);
		
		List<Map<String, Object>> list = new ArrayList<>();
		
		Mockito.when(summaryService.billingTransactionDetails(Mockito.anyString(), Mockito.anyList(),
				Mockito.anyInt())).thenReturn(list);
		//RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/details/{ime}/download").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(put("/billing/summary/details/{imeTraceId}","IME1")).andReturn();
		System.out.println(result.getResponse());

	}

}
