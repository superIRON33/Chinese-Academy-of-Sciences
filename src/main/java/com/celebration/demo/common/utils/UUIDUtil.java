package com.upuper.demo.common.utils;

import java.util.UUID;

/**
 * 使用UUID生成文件名
 * UUID含义是通用唯一识别码 (Universally Unique Identifier)
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
