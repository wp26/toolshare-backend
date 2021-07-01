package com.toolshare.toolshare.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.toolshare.toolshare.entity.File;
import com.toolshare.toolshare.repository.FileRepository;

/**
 * This class allows to store files in DB
 *
 * @author Paul
 */

@Service
public class FileStorageService {

    @Autowired
    private FileRepository fileDBRepository;

    /**
     * Stores files in DB
     * @param file part of multipart request with file
     * @return the saved Entity
     * @throws IOException
     */
    public File store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = new File(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }

    /**
     * Get file by its id.
     * @param id file id to get
     * @return File Entity
     */
    public File getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    /**
     * Get all Files from DB
     * @return Stream of file Objects
     */
    public Stream<File> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}