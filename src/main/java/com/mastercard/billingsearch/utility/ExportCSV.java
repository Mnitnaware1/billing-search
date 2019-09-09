package com.mastercard.billingsearch.utility;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;

import com.mastercard.billingsearch.model.CSVResponse;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ExportCSV {

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
}
