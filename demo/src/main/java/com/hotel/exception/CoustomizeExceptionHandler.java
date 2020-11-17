package com.hotel.exception;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;;

@ControllerAdvice
public class CoustomizeExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoustomizeExceptionHandler.class);

	/*
	 * @ExceptionHandler(value = { NullPointerException.class }) public
	 * ResponseEntity<Object> handleInvalidInputException(Exception ex) {
	 * LOGGER.error("Exception: ", ex.getMessage()); return new
	 * ResponseEntity<Object>(ex.getMessage(), HttpStatus.UNAUTHORIZED); }
	 */

	@ExceptionHandler(value = { Unauthorized.class })
	public ResponseEntity<Object> handleUnauthorizedException(Unauthorized ex) {
		LOGGER.error("Unauthorized Exception: ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
