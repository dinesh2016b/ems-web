/**
 * 
 */
package com.ems.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ems.exception.EMSException;
import com.ems.model.LoginRequest;
import com.ems.model.LoginResponse;
import com.ems.security.util.JwtUtil;
import com.ems.service.LoginService;
import com.ems.service.MyUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	public boolean authenticate(LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws EMSException {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
			log.info("-----> Authenicated");
			return authentication.isAuthenticated();
		} catch (BadCredentialsException e) {
			throw new EMSException("Incorrect username or password", e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EMSException(e);
		}
	}

	@Override
	public String createAuthenticationToken(LoginRequest loginRequest, HttpServletRequest httpServletRequest)
			throws EMSException {
		final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUserName());
		final String jwt_access_token = jwtTokenUtil.generateToken(userDetails);

		return jwt_access_token;
	}

	@Override
	public LoginResponse processLogout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws EMSException {
		httpServletRequest.getSession().removeAttribute("jwt_access_token");
		httpServletRequest.getSession().setAttribute("cookies", null);
		return null;
	}
}
