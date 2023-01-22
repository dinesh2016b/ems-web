/**
 * 
 */
package com.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.entity.UserDetails;

/**
 * @author dinesh
 *
 */

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
	
	UserDetails findUserByUserName(String userName);	
}