package com.ems.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
	private final String jwt_access_token;
}
