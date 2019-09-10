package com.mastercard.billingsearch.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mastercard.billingsearch.model.UserRoles;

@RestController
@RequestMapping("/roleApi")
public class RoleController {

	@GetMapping("/exportableRecords")
	public ResponseEntity<UserRoles> getExportableRecords(@RequestParam String userId,
			@RequestParam String feederType) {
		UserRoles userRoles = new UserRoles();
		//HOW to identify the fileds of which table
		String[] detailFields = { "IME_TRACE_ID", "MTI_FUNC_CD", "TRAN_TYPE_CD", "ISS_ICA_NUM", "ACQ_ICA_NUM","CARD_TYPE" };
		String[] asFields = { "IME", "FunctionCode", "TRXNTYPE", "IssuerICA", "AcuirerICA","CardType" };// we will get as this
		userRoles.setRoleName("BILL_OPS");
		userRoles.setDetailFields(detailFields);
		userRoles.setAsFields(asFields);
		userRoles.setDownloadSummaryCount(2);
		userRoles.setDownloadDetailCount("10000");
		userRoles.setDetailResponseCount("300");

		return new ResponseEntity<>(userRoles, HttpStatus.OK);

	}
}
