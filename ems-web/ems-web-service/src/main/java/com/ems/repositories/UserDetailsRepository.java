/**
 * 
 */
package com.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.entity.UserDetails;

/**
 * @author dinesh
 *
 */

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
	
	UserDetails findUserByUserName(String userName);	
}