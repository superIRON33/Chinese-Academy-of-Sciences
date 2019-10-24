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
     * 文本检测接口
     */
    CONTENT_DETECTION("https://api.weixin.qq.com/wxa/msg_sec_check?access_token="),
    
    /**
     * 小程序appid
     */
    APP_ID("wxb6de1fd8d30a4dea"),
    
    /**
     * 小程序secret
     */
    SECRET("02ba4c93d659fbde9d3c40d0a0dca454");
    
    private String value;
}