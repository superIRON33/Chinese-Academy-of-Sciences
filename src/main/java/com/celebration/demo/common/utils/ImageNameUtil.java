package com.celebration.demo.common.utils;

/**
 * 功能：生成随机文件名
 */
public class ImageNameUtil {

    /**
     * 获取文件后缀
     */
    private static String getSuffix(String imageName) {
        return imageName.substring(imageName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     */
    public static String getImageName(String imageOriginName) {
        return UUIDUtil.getUUID() + ImageNameUtil.getSuffix(imageOriginName);
    }

}
