package com.mastercard.billingsearch.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mastercard.billingsearch.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
@SpringBootTest
public class SummaryControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void billingSummaryDownloadTest() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post("/billing/summary/download").contentType(MediaType.APPLICATION_JSON)
						.content("{\r\n" + "	\"invoiceDate\":\"2019-08-29\",\r\n"
								+ "	\"billEventId\":\"BILLEVNTID1\",\r\n" + "	\"invoiceNumber\":\"INVNUM1\",\r\n"
								+ "	\"activityICA\":\"ICA1\",\r\n" + "	\"feederType\":\"AUTH\"\r\n" + "	\r\n"
								+ "	\r\n" + "}")
						.accept(MediaType.APPLICATION_JSON).header("userId", "mohit"))
				.andExpect(status().isOk());

	}

}
