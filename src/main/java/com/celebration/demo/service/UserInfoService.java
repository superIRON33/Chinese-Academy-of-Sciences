package com.celebration.demo.service;

import com.celebration.demo.model.dto.ResultDTO;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService {

    ResultDTO getUserInfo(String id);
    
    ResultDTO updateUserInfo(String id, String name, Integer year, String institute, String province, String degree, String workspace, Integer workspaceIs, String address, Integer addressIs, String telephone, Integer telephoneIs, String emailAdd, Integer emailAddIs, String slogan, String country);

    ResultDTO uploadWechatPNG(String id, MultipartFile image);
}
