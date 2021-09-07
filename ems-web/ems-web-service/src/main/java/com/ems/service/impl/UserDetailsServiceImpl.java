/**
 * 
 */
package com.ems.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dao.UserDetailsRepositoryDAO;
import com.ems.entity.UserDetails;
import com.ems.service.UserDetailsService;

/**
 * @author dinesh
 *
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDetailsRepositoryDAO userDetailsRepositoryDAO;

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
