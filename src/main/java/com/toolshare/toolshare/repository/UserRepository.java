package com.toolshare.toolshare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toolshare.toolshare.entity.User;

/**
 * Repository for all Files and custom Query's
 *
 * @author
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * find User by Username
	 * @param username username of user
	 * @return Array of User objects that can be null
	 */
	Optional<User> findByUsername(String username);

	/**
	 * check if username already exists
	 * @param username username of user
	 * @return true or false if user with username exists
	 */
	Boolean existsByUsername(String username);

	/**
	 * check if email already exists
	 * @param email email of user
	 * @return true or false if user with email exists
	 */
	Boolean existsByEmail(String email);
}
