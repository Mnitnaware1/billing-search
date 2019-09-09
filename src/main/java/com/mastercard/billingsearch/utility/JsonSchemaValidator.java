package com.mastercard.billingsearch.utility;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;

public class JsonSchemaValidator {
	
	public String validateJsonData(String jsonSchema,String jsonData)
			throws IOException, ProcessingException {

		JsonNode d = JsonLoader.fromString(jsonData);
		JsonNode s = JsonLoader.fromResource(jsonSchema);
		JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		JsonValidator v = factory.getValidator();
		ProcessingReport report = v.validate(s, d);
		return report.toString();

	}
}
