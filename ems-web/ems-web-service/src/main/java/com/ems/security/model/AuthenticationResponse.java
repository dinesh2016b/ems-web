package com.ems.security.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String jwt_access_token;

    public AuthenticationResponse(String jwt_access_token) {
        this.jwt_access_token = jwt_access_token;
    }

    public String getJwt() {
        return jwt_access_token;
    }
}
