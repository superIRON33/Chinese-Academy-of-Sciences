package com.celebration.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zbw
 * @date: 2019/7/30
 * @description: 封装返回给前端的UserInfo数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    private String name;
    private String image;
    private Integer learnTime;
    private String institute;
    private String degree;
    private String workspace;
    private String address;
    private String telephone;
    private String emailAdd;
    private String wechatPNG;
    private String slogan;
}
