package com.ems.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ems.bean.User;
import com.ems.exception.EMSException;
import com.ems.model.LoginRequest;
import com.ems.model.LoginResponse;
import com.ems.service.LoginService;
import com.ems.util.ApplicationConstants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600, allowCredentials = "true")
@Slf4j
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping(path = ApplicationConstants.ENDPOINT_SIGNIN, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest,
			@RequestHeader Map<String, String> headers, HttpServletRequest httpServletRequest) throws EMSException {

		String jwt_access_token = null;
		log.info("----> login EMS..");

		headers.forEach((key, value) -> {
			log.info(key + " : " + value);
		});
		
		LoginResponse loginResponse = null;
		HttpSession session = httpServletRequest.getSession();
		boolean isAuthenticated = loginService.authenticate(loginRequest);

		if (isAuthenticated) {
			User user = loginService.loadUserByUsername(loginRequest.getUserName());
			if (user != null) {
				jwt_access_token = loginService.createAuthenticationToken(user);
				session.setAttribute("jwt_access_token", jwt_access_token);
				session.setAttribute("cookies", jwt_access_token);
				session.setAttribute("user", user);
				log.info("---> jwt_access_token : " + session.getAttribute("jwt_access_token"));
				log.info("---> Cookies : " + session.getAttribute("cookies"));
				loginResponse = new LoginResponse(user, jwt_access_token);
			}
		}

		return ResponseEntity.ok(loginResponse);
	}
}