package com.ems.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EMSRequestResponseFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		httpServletRequest.setAttribute("EMS-Request-Filter-Attribute", "Testing header...");

		/*
		 * log.info("----> Remote Host: " + request.getRemoteHost());
		 * log.info("----> Remote Address: " + request.getRemoteAddr());
		 * log.info("----> Local Address: " + request.getLocalAddr());
		 * log.info("----> Local Port: " + request.getLocalPort());
		 */

		log.info("----> EMSRequestResponseFilter.doFilter()");

		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("EMS-Response-Filter-Header", "Testing header...");

		filterChain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
