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
	
	public boolean loginUser(UserDetails userDetails);

	public void saveUser(UserDetails userDetails);
}
