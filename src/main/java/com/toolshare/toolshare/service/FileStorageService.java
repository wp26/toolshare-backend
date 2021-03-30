package com.toolshare.toolshare.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.toolshare.toolshare.entity.File;
import com.toolshare.toolshare.repository.FileRepository;

@Service
public class FileStorageService {

    @Autowired
    private FileRepository fileDBRepository;

    public File store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = new File(fileName, file.getContentType(), file.getBytes());

        return fileDBRepository.save(FileDB);
    }

    public File getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<File> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}