package com.celebration.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: wjy
 * @Date: 2019/6/1 12:31
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
     * 发送模板消息
     */
    WX_TEMPLATE("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token="),
    
    /**
     * 获取二维码
     */
    WX_CODE("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="),
    
    /**
     * 小程序appid
     */
    APP_ID(/*"wx28ced5b5c54c689a"*/"wx4665950b83bead39"),
    
    /**
     * 小程序secret
     */
    SECRET(/*"ab0694dcc75c7da336a9d365ba724560"*/"1b3dcf13281fc1c1f6af3ef58ab12435"),
    
    /**
     * 发送模板消息的字段之一-templateid
     */
    TEMPLATE_ID("7lU8aVnHE3NnIPLl2u-u5wTLQbmAI6hn3E6FDQd-FiI"),
    
    /**
     * 发送模板消息的字段之一-template_responder
     */
    TEMPLATE_RESPONDER("西电小喇叭");
    
    private String value;
}