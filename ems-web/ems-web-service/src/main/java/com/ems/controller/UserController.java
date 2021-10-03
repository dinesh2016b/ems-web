/**
 * 
 */
package com.ems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.UserDetails;
import com.ems.service.EMSUserDetailsService;

/**
 * @author dinesh
 *
 */

@RestController
@RequestMapping("/users")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

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

		logger.info("------------> loginUser : " + loginFlag);
		if (loginFlag) {
			logger.info("------------> loginUser : " + userDetails.toString());
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
