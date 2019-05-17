package com.videotel.test.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Central place where all exceptions are handled. This handler is responsible
 * for dealing with exceptions occurring during API methods.
 *
 */
@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.error("Building Error JSON Response for Malformed Request..");
		return buildResponseEntity(new ErrorResponse(false, "Malformed JSON REquest"));
	}

	@ExceptionHandler({ UserException.class })
	@ResponseBody
	ResponseEntity<ErrorResponse> handleMissingArgumentException(UserException c) {
		LOGGER.error("Building UserException JSON Response for UserException..", c.getError());
		ErrorResponse error = new ErrorResponse(c.isSuccess(), c.getError());
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ UserNotFoundException.class })
	@ResponseBody
	ResponseEntity<ErrorResponse> handleDataNotFoundException(UserNotFoundException c) {
		LOGGER.error("Building UserNotFoundException JSON Response..", c.getError());
		ErrorResponse error = new ErrorResponse(c.isSuccess(), c.getError());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ InvalidInputException.class })
	@ResponseBody
	ResponseEntity<ErrorResponse> handleDataNotFoundException(InvalidInputException e) {
		LOGGER.error("Building InvalidInputException JSON Response for UserException..", e.getMessage());
		ErrorResponse error = new ErrorResponse(false, e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}