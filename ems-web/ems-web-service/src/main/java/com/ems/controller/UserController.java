/**
 * 
 */
package com.ems.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.UserDetails;
import com.ems.service.UserDetailsService;

/**
 * @author dinesh
 *
 */

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserController() {	
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();;
	}

	@PostMapping("/login")
	public void loginUser(@RequestBody UserDetails userDetails) {		
		boolean loginFlag = userDetailsService.loginUser(userDetails);
		userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		logger.info("------------> loginUser : "+loginFlag);
		logger.info("------------> loginUser : "+userDetails.toString());
	}
	
	@PostMapping("/saveUser")
	public void saveUser(@RequestBody UserDetails userDetails) {
		//userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		
	}
}
