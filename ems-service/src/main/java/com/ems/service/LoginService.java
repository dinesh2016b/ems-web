/**
 * 
 */
package com.ems.service;

import com.ems.bean.User;
import com.ems.exception.EMSException;
import com.ems.model.LoginRequest;
import com.ems.model.LoginResponse;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Dinesh
 *
 */
public interface LoginService {
	public boolean authenticate(LoginRequest loginRequest) throws EMSException;
	public String createAuthenticationToken(User user) throws EMSException;
	public User loadUserByUsername(String username) throws EMSException;
	public LoginResponse processLogout(HttpServletRequest httpServletRequest) throws EMSException;
}
