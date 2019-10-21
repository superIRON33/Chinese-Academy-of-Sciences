package com.celebration.demo.service.impl;

import com.celebration.demo.common.enums.ResultEnum;
import com.celebration.demo.model.dto.MapUserInfoDTO;
import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.model.dto.UserLocationDTO;
import com.celebration.demo.model.entity.UserInfo;
import com.celebration.demo.model.entity.UserLocate;
import com.celebration.demo.repository.UserInfoRepository;
import com.celebration.demo.repository.UserLocateRepository;
import com.celebration.demo.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
@Service
public class MapServiceImpl implements MapService {
    
    @Autowired
    private UserInfoRepository userInfoRepository;
    
    @Autowired
    private UserLocateRepository userLocateRepository;
    
    @Override
    public ResultDTO getMapUserInfo(String userId) {
        
        Optional<UserInfo> userInfo = userInfoRepository.findUserInfoById(userId);
        if (userInfo.isPresent()) {
            String workspace = "";
            String emailAdd = "";
            if (userInfo.get().getWorkspaceIs() == 0) {
                workspace = userInfo.get().getWorkspace();
            }
            if (userInfo.get().getEmailAddIs() == 0) {
                emailAdd = userInfo.get().getEmailAdd();
            }
            MapUserInfoDTO mapUserInfoDTO = new MapUserInfoDTO(userId, userInfo.get().getName(), userInfo.get().getImage(), userInfo.get().getYear(), userInfo.get().getInstitute(), workspace, emailAdd);
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(mapUserInfoDTO);
            return resultDTO;
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
    
    @Override
    public ResultDTO saveMapPosition(String userId, Float longitude, Float latitude, String province, String city, String region) {
    
        Optional<UserInfo> userInfo = userInfoRepository.findUserInfoById(userId);
        if (userInfo.isPresent()) {
            UserLocate userLocate = new UserLocate(userId, longitude, latitude, province, city, region);
            userLocateRepository.save(userLocate);
            return new ResultDTO(ResultEnum.SUCCESS);
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
    
    @Override
    public ResultDTO getMapPosition(Float neLongitude, Float neLatitude, Float swLongitude, Float swLatitude) {
        
        
        List<UserLocate> userLocates =  userLocateRepository.findAllByLongitudeBetweenAndLatitudeBetween(swLongitude, neLongitude, swLatitude, neLatitude);
        ArrayList<UserLocationDTO> lists = new ArrayList<>();
        userLocates.forEach(userLocate -> {
            Optional<UserInfo> userInfo = userInfoRepository.findUserInfoById(userLocate.getId());
            if (userInfo.isPresent()) {
                UserLocationDTO userLocationDTO = new UserLocationDTO(userLocate.getId(), userInfo.get().getImage(), userLocate.getLongitude(), userLocate.getLatitude());
                lists.add(userLocationDTO);
            }
        });
        if (lists != null) {
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(lists);
            return resultDTO;
        }
        else {
            return new ResultDTO(ResultEnum.ID_INVALID);
        }
    }
}
