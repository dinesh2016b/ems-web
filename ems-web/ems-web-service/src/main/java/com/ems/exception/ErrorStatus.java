package com.ems.exception;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.http.HttpStatus;

public class ErrorStatus {
	
	private HttpStatus serverStatusCode;
	private ServerityCode serverityCode;
	private ErrorDescriptionStatus[] errorDescriptionStatus;

	public ErrorStatus() {
		super();
	}

	public ErrorStatus(HttpStatus serverStatusCode, ServerityCode serverity, ErrorDescriptionStatus... errorDescriptionStatus) {
		super();
		this.setServerStatusCode(serverStatusCode);
		this.setServerityCode(serverity);
		this.setErrorDescriptionStatus(errorDescriptionStatus);
	}

	public HttpStatus getServerStatusCode() {
		return serverStatusCode;
	}

	public void setServerStatusCode(HttpStatus serverStatusCode) {
		this.serverStatusCode = serverStatusCode;
	}

	public ServerityCode getServerityCode() {
		return serverityCode;
	}

	public void setServerityCode(ServerityCode serverityCode) {
		this.serverityCode = serverityCode;
	}

	public ErrorDescriptionStatus[] getErrorDescriptionStatus() {
		return errorDescriptionStatus;
	}

	public void setErrorDescriptionStatus(ErrorDescriptionStatus[] errorDescriptionStatus) {
		this.errorDescriptionStatus = errorDescriptionStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(errorDescriptionStatus);
		result = prime * result + Objects.hash(serverStatusCode, serverityCode);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorStatus other = (ErrorStatus) obj;
		return Arrays.equals(errorDescriptionStatus, other.errorDescriptionStatus) && serverStatusCode == other.serverStatusCode
				&& Objects.equals(serverityCode, other.serverityCode);
	}

	@Override
	public String toString() {
		return "ErrorStatus [serverStatusCode=" + serverStatusCode + ", serverity=" + serverityCode + ", ErrorDescriptionStatus="
				+ Arrays.toString(errorDescriptionStatus) + "]";
	}
	
}
