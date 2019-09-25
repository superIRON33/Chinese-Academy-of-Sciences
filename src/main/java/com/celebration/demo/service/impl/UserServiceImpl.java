package com.celebration.demo.service.impl;

import com.celebration.demo.common.enums.ResultEnum;
import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.model.entity.UserInfo;
import com.celebration.demo.repository.UserInfoRepository;
import com.celebration.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public ResultDTO getUserInfo(String id) {

        Optional<UserInfo> userInfo = userInfoRepository.findUserInfoById(id);
        if (userInfo.isPresent()) {
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(userInfo.get());
            return resultDTO;
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
    
    @Override
    public ResultDTO updateUserInfo(String id, String name, Integer year, String institute, String degree, String workspace, Integer workspaceIs, String address, Integer addressIs, String telephone, Integer telephoneIs, String emailAdd, Integer emailIs, String wechatPNG, String slogan) {
    
        Optional<UserInfo> userInfo = userInfoRepository.findUserInfoById(id);
        if (userInfo.isPresent()) {
            userInfoRepository.save(new UserInfo(id, name, userInfo.get().getImage(), year, institute, degree, workspace, workspaceIs, address, addressIs, telephone, telephoneIs, emailAdd, emailIs, wechatPNG, slogan));
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            return resultDTO;
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
}
