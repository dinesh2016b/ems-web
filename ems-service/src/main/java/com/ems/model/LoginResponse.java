package com.ems.model;

import com.ems.bean.User;

public class LoginResponse {

	
	private User user;
	private boolean isAuthenticated;
	private final String jwt_access_token;
	
    public LoginResponse(User user, String jwt_access_token, boolean isAuthenticated) {
    	this.user = user;
        this.jwt_access_token = jwt_access_token;
        this.isAuthenticated = isAuthenticated;
    }
    
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
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
