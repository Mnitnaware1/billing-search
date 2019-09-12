package com.mastercard.billingsearch.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.SystemUtils;

public class Test {

	public static void main(String[] args) {
		String[] detailFields = { "IME_TRACE_ID", "MTI_FUNC_CD", "TRAN_TYPE_CD", "ISS_ICA_NUM", "ACQ_ICA_NUM",
				"CARD_TYPE" };
		String[] asFields = { "IME", "FunctionCode", "TRXNTYPE", "IssuerICA", "AcuirerICA", "CardType" };// we will get
																											// as this

		buildQuery(detailFields, asFields);
	}

	private static void buildQuery(String[] detailFields, String[] asFields) {

		Stream<String> concat = Stream.concat(Stream.of(detailFields), Stream.of(asFields));

		List<String> resultList = concat.collect(Collectors.toList());
		System.err.println(resultList);

	}
}
