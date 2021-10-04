package com.ems.exception;

public class APIException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public APIException(String message) {
		super(message);
	}

	public APIException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public APIException(Throwable throwable) {
		super(throwable);
	}

}
