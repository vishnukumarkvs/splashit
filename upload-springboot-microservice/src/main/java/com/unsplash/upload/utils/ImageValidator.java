package com.unsplash.upload.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImageValidator {

    public static boolean areImages(List<MultipartFile> files){
        for(MultipartFile file: files){
            String fileType = file.getContentType();
            if(!fileType.startsWith("image/")){
                return false;
            }
        }
        return true;
    }
}
