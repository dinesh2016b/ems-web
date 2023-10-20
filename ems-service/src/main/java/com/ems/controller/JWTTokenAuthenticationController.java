package com.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.exception.EMSException;
import com.ems.security.model.AuthenticationRequest;
import com.ems.security.model.AuthenticationResponse;
import com.ems.security.util.JwtUtil;
import com.ems.service.MyUserDetailsService;
import com.ems.util.ApplicationConstants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class JWTTokenAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@PostMapping(value = ApplicationConstants.ENDPOINT_AUTHENTICATE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws EMSException {
		log.info("-----> createAuthenticationToken");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			log.info("-----> Authenicated");
		} catch (BadCredentialsException e) {
			throw new EMSException("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt_access_token = jwtTokenUtil.generateToken(userDetails);

		httpServletRequest.getSession().setAttribute("jwt_access_token", jwt_access_token);
		
		/*Cookie jwtTokenCookie = new Cookie("jwt_access_token", jwt_access_token);

		jwtTokenCookie.setMaxAge(86400);
		jwtTokenCookie.setSecure(true);
		jwtTokenCookie.setHttpOnly(true);
		jwtTokenCookie.setPath("/apiservice/");
		jwtTokenCookie.setDomain("apiservice.com");

		httpServletResponse.addCookie(jwtTokenCookie);*/

		return ResponseEntity.ok(new AuthenticationResponse(jwt_access_token));
	}
}
