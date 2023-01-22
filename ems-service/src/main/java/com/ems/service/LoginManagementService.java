/**
 * 
 */
package com.ems.service;

import javax.servlet.http.HttpServletRequest;

import com.ems.bean.LoginRequest;
import com.ems.bean.LoginResponse;
import com.ems.exception.EMSException;

/**
 * @author Dinesh
 *
 */
public interface LoginManagementService {
	public LoginResponse loginProcess(LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws EMSException;

	public LoginResponse logoutProcess(LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws EMSException;
}
