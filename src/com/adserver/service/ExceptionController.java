package com.adserver.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Harisha Akasapu
 * Common exception handler to propagate exception with a meaningful error response.
 */
@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDomain> exceptionHandler(Exception ex) {
		ResponseDomain error = new ResponseDomain(HttpStatus.PRECONDITION_FAILED.value(), "Failure",  ex.getMessage());
		
		return new ResponseEntity<ResponseDomain>(error, HttpStatus.OK);
	}
}
