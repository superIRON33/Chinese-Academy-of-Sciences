package com.celebration.demo.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class ImageUploadUtil {

    public static String upload(MultipartFile image, String path, String imageName) {

        //生成新文件名
        String realPath = path + "/" + ImageNameUtil.getImageName(imageName);

        File file = new File(realPath);

        if(!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
        try {
            image.transferTo(file);
            return realPath;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "";
        }
    }
}
