/**
 * 
 */
package com.ems.security;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ems.filters.JwtRequestFilter;
import com.ems.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	/*
	 * @Bean public AuthenticationManager
	 * authenticationManager(AuthenticationConfiguration
	 * authenticationConfiguration) throws Exception { return
	 * authenticationConfiguration.getAuthenticationManager(); }
	 */

	/*
	 * @Bean AuthenticationManager
	 * authenticationManager(AuthenticationManagerBuilder builder,
	 * MyUserDetailsService myUserDetailsService, PasswordEncoder encoder) throws
	 * Exception { return
	 * builder.userDetailsService(myUserDetailsService).passwordEncoder(encoder).and
	 * ().build(); }
	 */

	// adding our custom authentication providers
	// authentication manager will call these custom provider's
	// authenticate methods from now on.

	/*
	 * @Autowired void registerProvider(AuthenticationManagerBuilder auth,
	 * PasswordEncoder encoder) throws Exception {
	 * auth.userDetailsService(myUserDetailsService).passwordEncoder(encoder); }
	 */

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder)
			throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(myUserDetailsService).passwordEncoder(noOpPasswordEncoder);
		return authenticationManagerBuilder.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// AuthenticationManager authenticationManager = authBuilder.build();

		httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers(antMatcher("/apiservice/authenticate")).authenticated();
					auth.requestMatchers(antMatcher("/apiservice/**")).permitAll();
					auth.requestMatchers(antMatcher("/h2-console/**")).permitAll();
					auth.anyRequest().authenticated();
				}).addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class).cors()
				.configurationSource(corsConfigurationSource());

		return httpSecurity.build();
	}

	/*
	 * @Override
	 * 
	 * @Bean public AuthenticationManager authenticationManagerBean() throws
	 * Exception { return super.authenticationManagerBean(); }
	 * 
	 * @Override protected void configure(HttpSecurity httpSecurity) throws
	 * Exception {
	 * 
	 * httpSecurity.csrf().disable().authorizeRequests()
	 * .antMatchers(HttpMethod.POST, "/apiservice/authenticate").permitAll()
	 * .antMatchers(HttpMethod.POST, "/apiservice/employees/ * * / *").permitAll()
	 * .antMatchers(HttpMethod.POST, "/apiservice/departments/ * * / *").permitAll()
	 * .antMatchers(HttpMethod.POST, "/apiservice/salaries/ * * / *").permitAll()
	 * .antMatchers(HttpMethod.POST, "/apiservice/login/ * * / *").permitAll()
	 * .antMatchers(HttpMethod.POST, "/apiservice/logout/ * * / * ").permitAll()
	 * .antMatchers("/h2-console/**").permitAll() .anyRequest().authenticated()
	 * //.and() //.formLogin().loginPage("/loginems").permitAll() .and()
	 * .logout().permitAll()
	 * .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
	 * STATELESS) .and().exceptionHandling();
	 * 
	 * httpSecurity.addFilterBefore(jwtRequestFilter,
	 * UsernamePasswordAuthenticationFilter.class);
	 * httpSecurity.cors().configurationSource(corsConfigurationSource()); }
	 */
	// @Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
		configuration.setAllowedHeaders(Collections.singletonList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}