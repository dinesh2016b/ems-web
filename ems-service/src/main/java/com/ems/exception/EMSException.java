package com.ems.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class EMSException extends Exception {

	private static final long serialVersionUID = 1L;
	private ErrorStatus errorStatus;

	public EMSException(Throwable throwable) {
		super(throwable);
	}

	public EMSException(ErrorStatus errorStatus) {
		this.errorStatus = errorStatus;
	}
	
	public EMSException(ErrorStatus errorStatus, String message) {
		super(message);
		this.errorStatus = errorStatus;
	}

	public EMSException(ErrorStatus errorStatus, String message, Throwable throwable) {
		super(message, throwable);
		this.errorStatus = errorStatus;
	}

	public EMSException(ErrorStatus errorStatus, Throwable throwable) {
		super(throwable);
		this.errorStatus = errorStatus;
	}

	public EMSException(String message, BadCredentialsException e) {
		super(message);
	}

	public ErrorStatus getErrorStatus() {
		return errorStatus;
	}
}
