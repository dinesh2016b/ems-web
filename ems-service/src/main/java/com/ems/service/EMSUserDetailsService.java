/**
 * 
 */
package com.ems.service;

import com.ems.entity.User;

/**
 * @author dinesh
 *
 */
public interface EMSUserDetailsService {
	
	boolean loginUser(User userDetails);

	void saveUser(User userDetails);
}
