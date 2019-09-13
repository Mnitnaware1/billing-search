package com.mastercard.billingsearch.utility;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

import com.mastercard.billingsearch.model.CSVResponse;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ExportCSV {
	
	private ExportCSV() {
		throw new IllegalStateException("Utility class");
	}

   /**
    * 
    * generates summary report
    * **/
	public static void export(HttpServletResponse response, List<CSVResponse> summaryReport, String fileName)
			throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		// set file name and content type
		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
 
		// create a csv writer
		StatefulBeanToCsv<CSVResponse> writer = null;
		try {
			writer = new StatefulBeanToCsvBuilder<CSVResponse>(response.getWriter())
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withSeparator(CSVWriter.DEFAULT_SEPARATOR)
					.withOrderedResults(false).build();
			if (writer != null) {
				writer.write(summaryReport);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * generates billing transaction details report
	 **/
	public static void exportCSV(HttpServletResponse response, List<Map<String, Object>> billingTransactionDetails,
			String transactionDetailFile) throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {

		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + transactionDetailFile + "\"");

		OutputStream outputStream = response.getOutputStream();
		try {
			List<String> headers = billingTransactionDetails.stream().flatMap(map -> map.keySet().stream()).distinct().collect(Collectors.toList());
			final StringBuilder sb = new StringBuilder();
			for (int i = 0; i < headers.size(); i++) {
				sb.append(headers.get(i));
				sb.append(i == headers.size()-1 ? "\n" : ",");
			}
			for (Map<String, Object> map : billingTransactionDetails) {
				for (int i = 0; i < headers.size(); i++) {
					sb.append(map.get(headers.get(i)));
					sb.append(i == headers.size()-1 ? "\n" : ",");
				}
			}
			outputStream.write(sb.toString().getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}


	}
}
