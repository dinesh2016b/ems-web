package com.ems.exception;

import java.util.Objects;

public class ErrorDescriptionStatus {

	private int statusCode;
	//private String serverStatusCode;
	//private DescrptionErrorServerity errorServerity;
	private String statusDescription;

	public ErrorDescriptionStatus(int statusCode, String serverStatusCode, DescrptionErrorServerity serverity,
			String statusDesc) {
		this(statusCode, serverity);
		//this.serverStatusCode = serverStatusCode;
		this.statusDescription = statusDesc;
	}

	public ErrorDescriptionStatus(int statusCode, DescrptionErrorServerity serverity) {
		this.statusCode = statusCode;
		//this.errorServerity = serverity;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/*
	 * public String getServerStatusCode() { return serverStatusCode; }
	 * 
	 * public void setServerStatusCode(String serverStatusCode) {
	 * this.serverStatusCode = serverStatusCode; }
	 * 
	 * public DescrptionErrorServerity getServerity() { return errorServerity; }
	 * 
	 * public void setServerity(DescrptionErrorServerity serverity) {
	 * this.errorServerity = serverity; }
	 */

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	@Override
	public int hashCode() {
		return Objects.hash(statusCode, statusDescription);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorDescriptionStatus other = (ErrorDescriptionStatus) obj;
		return statusCode == other.statusCode && Objects.equals(statusDescription, other.statusDescription);
	}

	@Override
	public String toString() {
		return "ErrorDescriptionStatus [statusCode=" + statusCode + ", statusDescription=" + statusDescription + "]";
	}
}
