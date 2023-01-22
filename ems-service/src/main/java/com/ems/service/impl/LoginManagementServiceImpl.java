/**
 * 
 */
package com.ems.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ems.bean.LoginRequest;
import com.ems.bean.LoginResponse;
import com.ems.exception.EMSException;
import com.ems.service.LoginManagementService;

@Service
public class LoginManagementServiceImpl implements LoginManagementService {

	@Override
	public LoginResponse loginProcess(LoginRequest loginRequest, HttpServletRequest httpServletRequest)
			throws EMSException {
		
		String ems_cookies = (String) httpServletRequest.getSession().getAttribute("jwt_access_token");
		httpServletRequest.getSession().setAttribute("cookies", ems_cookies);
		return null;
	}

	@Override
	public LoginResponse logoutProcess(LoginRequest loginRequest, HttpServletRequest httpServletRequest)
			throws EMSException {
		// TODO Auto-generated method stub
		return null;
	}
}
