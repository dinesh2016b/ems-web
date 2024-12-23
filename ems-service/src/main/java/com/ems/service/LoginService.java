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
	boolean authenticate(LoginRequest loginRequest) throws EMSException;
	String createAuthenticationToken(User user) throws EMSException;
	User loadUserByUsername(String username) throws EMSException;
	LoginResponse processLogout(HttpServletRequest httpServletRequest) throws EMSException;
}
