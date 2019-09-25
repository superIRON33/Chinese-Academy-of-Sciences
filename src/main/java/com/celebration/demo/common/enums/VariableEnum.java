package com.celebration.demo.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: wjy
 * @Date: 2019/9/25 12:31
 * @Description: 项目常量值枚举类
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
     * 分页数
     */
    AMOUNT(10),
    
    /**
     * 登录超时时间-发布版(7天，单位为秒)
     */
    LOGIN_TIMEOUT(7 * 24 * 60 * 60),
    
    /**
     * 登录超时时间-测试版(2分钟，单位为秒)
     */
    LOGIN_TIMEOUT_TEST(2 * 60);

    private Integer value;
}