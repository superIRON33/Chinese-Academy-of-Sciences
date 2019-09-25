package com.celebration.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: wjy
 * @date: 2019/8/1
 * @description: 项目常量值枚举类
 */
@Getter
@AllArgsConstructor
public enum VariableEnum {

    /**
     * 未删除
     */
    OK(0),

    /**
     * 已删除
     */
    DELETE(1),

    /**
     * 每次请求的问答/问题/失物数的最大条数
     */
    AMOUNT(8),
    
    /**
     * 首页最新问答数目(不分页)
     */
    HOME_AMOUNT(15),
    
    /**
     * 登录超时时间-发布版(7天，单位为秒)
     */
    LOGIN_TIMEOUT(7 * 24 * 60 * 60),
    
    /**
     * 登录超时时间-测试版(2分钟，单位为秒)
     */
    LOGIN_TIMEOUT_TEST(2 * 60),
    
    /**
     * 每天删除失物的最大次数
     */
    MAX_DELETE_COUNT(2),
    
    /**
     * user_count字段的默认值
     */
    USER_COUNT_DEFAULT(0);

    private Integer value;
}