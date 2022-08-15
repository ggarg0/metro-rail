package com.demo.metrorail.exceptions;

public class CardNumberNotFoundException extends RuntimeException {
	public CardNumberNotFoundException() {
	}

	public CardNumberNotFoundException(String message) {
		super(message);
	}

	public CardNumberNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
