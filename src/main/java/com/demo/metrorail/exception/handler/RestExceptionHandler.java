package com.demo.metrorail.exception.handler;

import com.demo.metrorail.constant.MessageConstants;
import com.demo.metrorail.exception.formatter.APIError;
import com.demo.metrorail.exceptions.CardNumberNotFoundException;
import com.demo.metrorail.exceptions.InsufficientFundsException;
import com.demo.metrorail.exceptions.StationDetailsNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing";
		return buildResponseEntity(new APIError(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new APIError(HttpStatus.BAD_REQUEST, error, ex));
	}
	
	
	/**
	 * This method will handle @Valid annotation exception handling
	 *
	 * @param ex
	 * @param headers
	 * @param status
	 * @param request
	 * @return
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		return buildResponseEntity(new APIError(HttpStatus.BAD_REQUEST, errors.toString(), ex));
	}

	@ExceptionHandler(CardNumberNotFoundException.class)
	public ResponseEntity<Object> handleCardNumberNotFoundException(CardNumberNotFoundException ex, HttpServletResponse response) {
		System.out.println("handleCardNumberNotFoundException called");
		return buildResponseEntity(new APIError(HttpStatus.INTERNAL_SERVER_ERROR, MessageConstants.CardNumberNotFound, ex));
	}

	@ExceptionHandler(StationDetailsNotFoundException.class)
	public ResponseEntity<Object> hanldeStationDetailsNotFoundException(StationDetailsNotFoundException ex, HttpServletResponse response) {
		System.out.println("hanldeStationDetailsNotFoundException called");
		return buildResponseEntity(new APIError(HttpStatus.INTERNAL_SERVER_ERROR, MessageConstants.StationDetailsNotFound, ex));
	}

	@ExceptionHandler(InsufficientFundsException.class)
	public ResponseEntity<Object> handleInsufficientFundsException(InsufficientFundsException ex, HttpServletResponse response)  {
		System.out.println("handleInsufficientFundsException called");
		return buildResponseEntity(new APIError(HttpStatus.INTERNAL_SERVER_ERROR, MessageConstants.InsufficientAmountInAccountMessage, ex));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex, HttpServletResponse response)  {
		System.out.println("handleException called");
		return buildResponseEntity(new APIError(HttpStatus.INTERNAL_SERVER_ERROR, MessageConstants.ContactAdmin, ex));
	}

	private ResponseEntity<Object> buildResponseEntity(APIError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
