package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.service.LoginService;
import com.ems.util.ApplicationConstants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600, allowCredentials="true")
@Slf4j
public class LogoutController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping(path = ApplicationConstants.ENDPOINT_LOGOUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		try {
			log.info("----> logout ");			

			loginService.processLogout(httpServletRequest, httpServletResponse);
			
			return ResponseEntity.ok().body("Bye");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}