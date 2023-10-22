package com.ems.model;

public class LoginResponse {

	private boolean isLogin;
	private final String jwt_access_token;

    public LoginResponse(String jwt_access_token) {
        this.jwt_access_token = jwt_access_token;
    }
    
	public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

    public String getJwt() {
        return jwt_access_token;
    }
}
