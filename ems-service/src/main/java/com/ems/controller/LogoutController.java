package com.ems.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.util.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600, allowCredentials="true")
@Slf4j
public class LogoutController {

	@PostMapping(path = ApplicationConstants.ENDPOINT_LOGOUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> logout() throws Exception {
		try {
			log.info("----> logout ");			

			return ResponseEntity.ok().body("Bye");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}