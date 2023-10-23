package com.ems.model;

import com.ems.bean.User;

public class LoginResponse {

	private boolean isLogin;
	private final String jwt_access_token;
	private User user;	

    public LoginResponse(User user, String jwt_access_token) {
    	this.user = user;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
