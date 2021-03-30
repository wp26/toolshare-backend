package com.toolshare.toolshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.toolshare.toolshare.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, String> {

}