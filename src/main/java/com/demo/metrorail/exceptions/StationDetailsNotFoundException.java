package com.demo.metrorail.exceptions;

public class StationDetailsNotFoundException extends RuntimeException {
	public StationDetailsNotFoundException() {
	}

	public StationDetailsNotFoundException(String message) {
		super(message);
	}

	public StationDetailsNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
