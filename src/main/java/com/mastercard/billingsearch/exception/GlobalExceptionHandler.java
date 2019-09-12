package com.mastercard.billingsearch.exception;

import static com.mastercard.billingsearch.utility.Constants.CHECK_LOGS_ROOT_CAUSE;
import static com.mastercard.billingsearch.utility.Constants.ERRORS;
import static com.mastercard.billingsearch.utility.Constants.MESSAGE;
import static com.mastercard.billingsearch.utility.Constants.RECORD_NOT_FOUND;
import static com.mastercard.billingsearch.utility.Constants.SERVER_ERROR;
import static com.mastercard.billingsearch.utility.Constants.TIMESTAMP;
import static com.mastercard.billingsearch.utility.Constants.VALIDATION_FAILED;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class, IOException.class, DataAccessException.class})
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails exceptionResponse = new ErrorDetails(SERVER_ERROR, CHECK_LOGS_ROOT_CAUSE,
                new Date());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(MESSAGE, VALIDATION_FAILED);
        body.put(ERRORS, fieldErrors);
        body.put(TIMESTAMP, new Date());
        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(RECORD_NOT_FOUND, ex.getMessage(), new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}