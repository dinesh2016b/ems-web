/**
 * 
 */
package com.ems.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ems.entity.UserDetails;
import com.ems.repositories.UserDetailsRepository;
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
	private UserDetailsRepository userDetailsRepositoryDAO;

	@Override
	public boolean loginUser(UserDetails userDetails) {

		UserDetails userDetailsDB = userDetailsRepositoryDAO.findUserByUserName(userDetails.getUserName());

		if (userDetailsDB != null && userDetailsDB.getPassword().equals(userDetails.getPassword())) {
			return true;
		}

		return false;
	}

	@Override
	public void saveUser(UserDetails userDetails) {

		userDetailsRepositoryDAO.save(userDetails);
	}

}
