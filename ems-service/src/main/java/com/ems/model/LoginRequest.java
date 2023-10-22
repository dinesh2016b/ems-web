package com.ems.model;

public class LoginRequest {

	private String userName;
	private String password;
	private boolean isLoogin;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoogin() {
		return isLoogin;
	}

	public void setLoogin(boolean isLoogin) {
		this.isLoogin = isLoogin;
	}
}
