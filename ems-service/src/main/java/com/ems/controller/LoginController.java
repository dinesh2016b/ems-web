package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.bean.LoginRequest;
import com.ems.bean.LoginResponse;
import com.ems.exception.EMSException;
import com.ems.service.LoginService;
import com.ems.util.ApplicationConstants;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600, allowCredentials = "true")
@Slf4j
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping(path = ApplicationConstants.ENDPOINT_LOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws Exception {
		LoginResponse loginResponse;
		try {
			log.info("----> login EMS..");
			
			loginResponse = new LoginResponse();
			boolean isAuthenticated = loginService.authenticate(loginRequest, httpServletRequest);
			
			if(isAuthenticated) {
				final String jwt_access_token = loginService.createAuthenticationToken(loginRequest, httpServletRequest);
				
				httpServletRequest.getSession().setAttribute("jwt_access_token", jwt_access_token);
				httpServletRequest.getSession().setAttribute("cookies", jwt_access_token);
			}
			log.info("---> jwt_access_token : "+httpServletRequest.getSession().getAttribute("jwt_access_token"));
			log.info("---> Cookies : "+httpServletRequest.getSession().getAttribute("cookies"));
		} catch (EMSException e) {
			log.error("EMS exception: " + e.getMessage());
			throw e;
		}

		return ResponseEntity.ok(loginResponse);
	}
}