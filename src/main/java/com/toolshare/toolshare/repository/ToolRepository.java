package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Repository for all Files and custom Query's
 *
 * @author Luca
 */

public interface ToolRepository extends JpaRepository<Tool, Integer> {
    /**
     * Get all Tools by name
     * @param name String to be searched
     * @return Array of Tool objects
     */
    Iterable<Tool> findByName(String name);

    /**
     * Get all Tools by availability
     * @param b true/false if available
     * @return Array of Tools
     */
    Iterable<Tool> findByIsAvailable(boolean b);

    /**
     * Get all Tools of user
     * @param user user id of user to find all tools
     * @return Array of Tools
     */
    Iterable<Tool> findByUser(User user);
}