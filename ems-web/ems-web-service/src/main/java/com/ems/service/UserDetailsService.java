/**
 * 
 */
package com.ems.service;

import com.ems.entity.UserDetails;

/**
 * @author dinesh
 *
 */
public interface UserDetailsService {
	
	public boolean loginUser(UserDetails userDetails);

	public void saveUser(UserDetails userDetails);
}
