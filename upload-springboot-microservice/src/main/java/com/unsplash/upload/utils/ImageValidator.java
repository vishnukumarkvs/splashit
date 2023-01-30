package com.unsplash.upload.utils;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImageValidator {

    public static boolean areImages(List<MultipartFile> files){
        System.out.println(files);
        if(files==null){
            return false;
        }
        for(MultipartFile file: files){
            String fileType = file.getContentType();
            if(!fileType.startsWith("image/")){
                return false;
            }
        }
        return true;
    }
}
