/**
 * 
 */
package com.ems.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ems.bean.LoginRequest;
import com.ems.bean.LoginResponse;
import com.ems.exception.EMSException;

/**
 * @author Dinesh
 *
 */
public interface LoginService {
	public boolean authenticate(LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws EMSException;
	public String createAuthenticationToken(LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws EMSException;
	public LoginResponse processLogout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws EMSException;
}
