package com.toolshare.toolshare.repository;

import com.toolshare.toolshare.entity.Tool;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Luca
 */

public interface ToolRepository extends CrudRepository<Tool, Integer> {
    Iterable<Tool> findByName(String name);
    Iterable<Tool> findByIsAvailable(boolean b);
}