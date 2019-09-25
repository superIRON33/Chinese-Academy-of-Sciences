package com.celebration.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wjy
 * @date: 2019/8/1
 * @description: 异常返回枚举类
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    /**
     * 请求失败
     */
    FAIL(-1, "请求失败"),
    
    /**
     * 正常返回
     */
    SUCCESS(0, "正常返回"),
    
    /**
     * sessionId无效
     */
    SESSION_ID_INVALID(20001, "sessionId无效"),
    
    /**
     * formId无效
     */
    FORM_ID_INVALID(20002, "formId无效"),
    
    /**
     * manager已存在
     */
    MANAGER_IS(20003, "manager已存在"),
    
    /**
     * 超级管理员登录
     */
    SUPER_MANAGER(20004, "超级管理员登录"),
    
    /**
     * 普通管理员登录
     */
    MANAGER(20005, "普通管理员登录"),
    
    /**
     * 密码错误
     */
    PASSWORD_WRONG(20006, "密码错误"),
    
    /**
     * 手机号不存在
     */
    PHONE_INVALID(20007, "手机号不存在"),
    
    /**
     * 每天只能删除两次
     */
    DELETE_ENOUGH(20008, "每天只能删除两次"),
    

    /**
     * id不存在
     */
    ID_INVALID(20009, "id不存在"),

    /**
     * 用户不存在
     */
    USER_INVALID(20010, "用户不存在");

    private Integer code;
    private String message;
}