package com.celebration.demo.service.impl;

import com.celebration.demo.common.enums.ResultEnum;
import com.celebration.demo.common.utils.ImageUploadUtil;
import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.model.entity.UserInfo;
import com.celebration.demo.repository.UserInfoRepository;
import com.celebration.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Value("${web.wechatPNG}")
    private String path;

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
    public ResultDTO updateUserInfo(String id, String name, Integer year, String institute, String degree, String workspace, Integer workspaceIs, String address, Integer addressIs, String telephone, Integer telephoneIs, String emailAdd, Integer emailAddIs, String slogan, String country) {
    
        Optional<UserInfo> userInfo = userInfoRepository.findUserInfoById(id);
        if (userInfo.isPresent()) {
            userInfoRepository.save(new UserInfo(id, name, userInfo.get().getImage(), year, institute, degree, workspace, workspaceIs, address, addressIs, telephone, telephoneIs, emailAdd, emailAddIs, userInfo.get().getWechatPNG(), slogan, country));
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            return resultDTO;
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
    
    @Override
    public ResultDTO uploadWechatPNG(String id, MultipartFile image) {
        
        if (userInfoRepository.findUserInfoById(id).isPresent()) {
            String[] strings = ImageUploadUtil.upload(image, path, image.getOriginalFilename());
            if (strings != null) {
                String imageURL = "https://action.ucas.ac.cn/wechatPNG/" + strings[1];
                System.out.println(imageURL);
                userInfoRepository.updateWechatPNG(id, imageURL);
                Map<String, Object> map = new HashMap<>();
                map.put("imageURL", imageURL);
                ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
                resultDTO.setData(map);
                return resultDTO;
            }
            else {
                ResultDTO resultDTO = new ResultDTO(ResultEnum.IMAGE_UPLOAD_FAILURE);
                resultDTO.setData(null);
                return resultDTO;
            }
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
}
