package com.celebration.demo.service;

import com.celebration.demo.model.dto.ResultDTO;

/**
 * @Auther: wjy
 * @Date: 2019/9/25 12:31
 * @Description: 微信登录服务层接口
 */
public interface WxService {

	String getAccessToken();
    
    ResultDTO isWxLogin(String code, String userId, String image);
    
    ResultDTO wxLogin(String userId, String code);
}
