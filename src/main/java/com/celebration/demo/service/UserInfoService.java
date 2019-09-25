package com.celebration.demo.service;

import com.celebration.demo.model.dto.ResultDTO;

public interface UserInfoService {

    ResultDTO getUserInfo(String id);
    
    ResultDTO updateUserInfo(String id, String name, Integer year, String institute, String degree, String workspace, Integer workspaceIs, String address, Integer addressIs, String telephone, Integer telephoneIs, String emailAdd, Integer emailIs, String wechatPNG, String slogan);
}
