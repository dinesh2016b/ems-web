/**
 * 
 */
package com.ems.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ems.filters.JwtRequestFilter;
import com.ems.service.MyUserDetailsService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}
	
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { auth.userDetailsService(myUserDetailsService); }
	 */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
	
		httpSecurity.csrf().disable()
			.authorizeRequests().antMatchers("/authenticate").permitAll()
			.anyRequest().authenticated().and().exceptionHandling().and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
		httpSecurity.cors().configurationSource(corsConfigurationSource());
	}
	
	 @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(Arrays.asList("*"));
	        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
	        configuration.setAllowedHeaders(Collections.singletonList("*"));
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }
}

/*
 * @Configuration public class SecurityConfiguration extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Override protected void configure(HttpSecurity httpSecurity) throws
 * Exception {
 * httpSecurity.authorizeRequests().antMatchers("/").permitAll().and().
 * authorizeRequests() .antMatchers("/console/**").permitAll();
 * httpSecurity.csrf().disable();
 * httpSecurity.headers().frameOptions().disable(); } }
 */