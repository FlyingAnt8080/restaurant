package com.lzy.liujing.restaurant.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImageUtil {
    public static String saveImage(MultipartFile multipartFile,String path) throws IOException {
        //文件路径
        File filePath = new File(path);
        //如果路径没有创建，先创建
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        String rootFileName = multipartFile.getOriginalFilename();
        String suffix =  rootFileName.substring(rootFileName.lastIndexOf(".")+1);
        String fileName = UUID.randomUUID() +"."+suffix;
        //创建文件
        File file = new File(path+File.separator+fileName);
        multipartFile.transferTo(file);
        return fileName;
    }
}
