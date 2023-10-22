/**
 * 
 */
package com.ems.service;

import com.ems.exception.EMSException;
import com.ems.model.LoginRequest;
import com.ems.model.LoginResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Dinesh
 *
 */
public interface LoginService {
	public boolean authenticate(LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws EMSException;
	public String createAuthenticationToken(LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws EMSException;
	public LoginResponse processLogout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws EMSException;
}
