package com.ems.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ems.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);

    Boolean existsByEmail(String email);

    User findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);
}