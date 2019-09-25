package com.celebration.demo.common.utils;

/**
 * 功能：生成随机文件名
 */
public class FileNameUtil {

    /**
     * 获取文件后缀
     */
    private static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     */
    public static String getFileName(String fileOriginName) {
        return UUIDUtil.getUUID() + FileNameUtil.getSuffix(fileOriginName);
    }

}
