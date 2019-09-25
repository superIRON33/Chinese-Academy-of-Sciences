package com.celebration.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: wjy
 * @Date: 2019/9/25 12:31
 * @Description: 微信小程序相关配置枚举类
 */
@Getter
@AllArgsConstructor
public enum WechatEnum {
    
    /**
     * 微信登录
     */
    WX_LOGIN("https://api.weixin.qq.com/sns/jscode2session"),
    
    /**
     * 获取access_token
     */
    WX_ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token"),
    
    /**
     * 获取二维码
     */
    WX_CODE("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="),
    
    /**
     * 小程序appid
     */
    APP_ID("wx28ced5b5c54c689a"),
    
    /**
     * 小程序secret
     */
    SECRET("20de0fa45c8f217976ab0a48f76a0801");
    
    private String value;
}