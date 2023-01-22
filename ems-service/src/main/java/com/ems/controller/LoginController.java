package com.ems.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.ems.service.LoginManagementService;
import com.ems.util.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600, allowCredentials = "true")
@Slf4j
public class LoginController {

	@Autowired
	private LoginManagementService loginManagementService;

	@PostMapping(path = ApplicationConstants.ENDPOINT_LOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) throws Exception {
		LoginResponse loginResponse;
		try {
			log.info("----> login EMS..");
			loginResponse = loginManagementService.loginProcess(loginRequest, httpServletRequest);
			log.info("---> Cookies : "+httpServletRequest.getSession().getAttribute("jwt_access_token"));
			log.info("---> Cookies : "+httpServletRequest.getSession().getAttribute("ems_cookies"));
		} catch (EMSException e) {
			log.error("EMS exception: " + e.getMessage());
			throw e;
		}

		return ResponseEntity.ok(loginResponse);
	}
}