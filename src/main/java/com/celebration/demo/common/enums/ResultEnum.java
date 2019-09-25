package com.celebration.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: wjy
 * @Date: 2019/9/25 12:31
 * @Description: 异常返回枚举类
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    
    /**
     * 正常返回
     */
    SUCCESS(200, "正常返回"),
    
    /**
     * 微信接口请求失败
     */
    WECHAT_FAIL(505, "微信接口请求失败"),
    
    /**
     * sessionId无效
     */
    SESSION_ID_INVALID(506, "sessionId无效"),

    /**
     * id不存在
     */
    ID_INVALID(507, "id不存在");

    private Integer code;
    private String message;
}