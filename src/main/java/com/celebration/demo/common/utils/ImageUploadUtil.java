package com.celebration.demo.common.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class ImageUploadUtil {

    public static String[] upload(MultipartFile image, String path, String imageName) {

        //生成新文件名
        String name = ImageNameUtil.getImageName(imageName);
        String realPath = path + "/" + name;

        File file = new File(realPath);

        if(!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
        try {
            image.transferTo(file);
            return new String[]{realPath, name};
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
