package org.example.springboot.report_36_회원가입_로그인.service;

import org.example.springboot.report_36_회원가입_로그인.exception.BoardNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@Service
public class FileService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public void deleteFile(String filePath) {
        if(filePath == null || filePath.isBlank())return;
        File file = new File(filePath);
        if(file.exists())file.delete();
    }

    public Resource downloadFile(String fileName){
        try{
            File file = new File(new File(uploadDir).getAbsoluteFile(), fileName);
            UrlResource resource = new UrlResource(file.toURI());
            if(!resource.exists() || !resource.isReadable()){
                throw new BoardNotFoundException("파일을 찾을 수 없습니다. fileName=" + fileName);
            }
            return resource;
        } catch (MalformedURLException e) {
            throw new IllegalStateException("파일 경로가 잘못되었습니다.", e);
        }
    }

    public String storeFile(MultipartFile file){
        if(file==null|| file.isEmpty())return null;
        try{
            File dir = new File(uploadDir).getAbsoluteFile();
            if(!dir.exists())dir.mkdirs();

            String storedFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File dest = new File(dir, storedFileName);

            file.transferTo(dest);

            return dest.getPath();
        } catch (IOException e) {
            throw new IllegalStateException("파일 저장에 실패 했습니다.", e);
        }
    }
}
