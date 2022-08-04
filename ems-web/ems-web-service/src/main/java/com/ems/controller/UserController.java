/**
 * 
 */
package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.UserDetails;
import com.ems.service.EMSUserDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dinesh
 *
 */

//@CrossOrigin(origins = "https://localhost:8080", maxAge = 3600, allowCredentials="true")
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private EMSUserDetailsService userDetailsService;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController() {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	@PostMapping("/login")
	public ResponseEntity<UserDetails> loginUser(@RequestBody UserDetails userDetails) {
		userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		boolean loginFlag = userDetailsService.loginUser(userDetails);

		log.info("------------> loginUser : " + loginFlag);
		if (loginFlag) {
			log.info("------------> loginUser : " + userDetails.toString());
			return ResponseEntity.ok().body(userDetails);
		}
		
		return ResponseEntity.ok().build();
	}

	@PostMapping("/saveUser")
	public void saveUser(@RequestBody UserDetails userDetails) {
		userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		userDetailsService.saveUser(userDetails);
	}
}
