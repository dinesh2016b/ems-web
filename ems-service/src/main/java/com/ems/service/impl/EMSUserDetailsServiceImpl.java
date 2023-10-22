/**
 * 
 */
package com.ems.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ems.entity.User;
import com.ems.repositories.UserRepository;
import com.ems.service.EMSUserDetailsService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dinesh
 *
 */

@Slf4j
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = true, timeout = 20)
public class EMSUserDetailsServiceImpl implements EMSUserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean loginUser(User userDetails) {

		User userDetailsDB = userRepository.findByUsername(userDetails.getUsername());

		return userDetailsDB != null && userDetailsDB.getPassword().equals(userDetails.getPassword());
	}

	@Override
	public void saveUser(User userDetails) {
		userRepository.save(userDetails);
	}

}
