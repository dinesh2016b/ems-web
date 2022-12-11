package com.ems.exception;

public class ApplicationErrorResponse {
	
	private ErrorStatus errorStatus;

	public ErrorStatus getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(ErrorStatus errorStatus) {
		this.errorStatus = errorStatus;
	}
}
