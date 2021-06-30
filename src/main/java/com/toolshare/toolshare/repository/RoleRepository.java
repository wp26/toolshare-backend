package com.toolshare.toolshare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toolshare.toolshare.entity.ERole;
import com.toolshare.toolshare.entity.Role;

/**
 * Repository for all Files and custom Query's
 *
 * @author
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	/**
	 * Get Roles that match role name
	 * @param name name of ERole to be found
	 * @return Array of Role objects that can be null
	 */
	Optional<Role> findByName(ERole name);
}
