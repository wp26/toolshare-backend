package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.entity.Tool;
import com.toolshare.toolshare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Luca
 */

public interface ToolRepository extends JpaRepository<Tool, Integer> {
    Iterable<Tool> findByName(String name);
    Iterable<Tool> findByIsAvailable(boolean b);
    Iterable<Tool> findByUser(User user);
}