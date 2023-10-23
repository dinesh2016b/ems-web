/**
 * 
 */
package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.bean.User;
import com.ems.service.EMSUserDetailsService;
import com.ems.service.impl.UserDetailsServiceImpl;
import com.ems.util.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dinesh
 *
 */

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600, allowCredentials = "true")
@RestController
@Slf4j
public class UserProfileController {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	EMSUserDetailsService emsUserDetailsService;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserProfileController() {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	@PostMapping(path = ApplicationConstants.ENDPOINT_GET_USER_PROFILE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserProfile(@RequestBody User user) {

		log.info(" getUserProfile -----> " + user.getUsername());
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

		log.info("------------> loginUser : " + userDetails.getUsername());
		if (userDetails != null) {
			log.info("------------> loginUser : " + userDetails);
			return ResponseEntity.ok().body(user);
		}

		return ResponseEntity.ok().build();
	}

	@PostMapping(path = ApplicationConstants.ENDPOINT_SAVE_USER_PROFILE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody User user) {
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), "", user.getAuthorities());
		//userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
		//emsUserDetailsService.saveUser(userDetails);
	}
}
