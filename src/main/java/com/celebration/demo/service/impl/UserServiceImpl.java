package com.celebration.demo.service.impl;

import com.celebration.demo.common.enums.ResultEnum;
import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.model.dto.UserInfoDTO;
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
            UserInfoDTO userInfoDTO = new UserInfoDTO(userInfo.get().getName(), userInfo.get().getImage(),
                    userInfo.get().getLearnTime(), userInfo.get().getInstitute(), userInfo.get().getDegree(),
                    userInfo.get().getWorkspace(), userInfo.get().getAddress(), userInfo.get().getTelephone(),
                    userInfo.get().getEmailAdd(), userInfo.get().getWechatPNG(), userInfo.get().getSlogan());

            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(userInfoDTO);
            return resultDTO;
        } else {
            return new ResultDTO(ResultEnum.USER_INVALID);
        }
    }
}
