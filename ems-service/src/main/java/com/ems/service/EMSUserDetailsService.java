/**
 * 
 */
package com.ems.service;

import com.ems.entity.UserDetails;

/**
 * @author dinesh
 *
 */
public interface EMSUserDetailsService {
	
	boolean loginUser(UserDetails userDetails);

	void saveUser(UserDetails userDetails);
}
