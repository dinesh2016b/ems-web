package com.ems.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ems.security.util.JwtUtil;
import com.ems.service.MyUserDetailsService;
import com.ems.util.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
			throws ServletException, IOException {

		/*
		 * final String authorizationHeader =
		 * httpServletRequest.getHeader("Authorization"); String username = null; String
		 * jwt = null;
		 * 
		 * if (authorizationHeader != null && authorizationHeader.startsWith("Bearer"))
		 * { jwt = authorizationHeader.substring(7); username =
		 * jwtUtil.extractUsername(jwt); } else {
		 * log.error("------> AuthorizationFilter: failed. Logout... ");
		 * RequestDispatcher requestDispatcher =
		 * httpServletRequest.getRequestDispatcher(ApplicationConstants.ENDPOINT_LOGOUT)
		 * ; requestDispatcher.forward(httpServletRequest, httpServletResponse); }
		 * 
		 * if (username != null &&
		 * SecurityContextHolder.getContext().getAuthentication() == null) { UserDetails
		 * userDetails = this.myUserDetailsService.loadUserByUsername(username); if
		 * (jwtUtil.validateToken(jwt, userDetails)) {
		 * 
		 * UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
		 * UsernamePasswordAuthenticationToken( userDetails, null,
		 * userDetails.getAuthorities()); usernamePasswordAuthenticationToken
		 * .setDetails(new
		 * WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
		 * SecurityContextHolder.getContext().setAuthentication(
		 * usernamePasswordAuthenticationToken); } }
		 */
		
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}