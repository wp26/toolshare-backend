package com.toolshare.toolshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toolshare.toolshare.entity.File;

/**
 * Repository for all Files and custom Query's
 *
 * @author
 */

@Repository
public interface FileRepository extends JpaRepository<File, String> {

}