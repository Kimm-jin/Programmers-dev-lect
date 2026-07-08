package com.example.spring.basicboard.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    public String storeFile(MultipartFile file){
        return null;
    }
}
