package com.celebration.demo.service;

import com.celebration.demo.model.dto.ResultDTO;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
public interface MapService {
    
    ResultDTO getMapUserInfo(String userId);
    
    ResultDTO saveMapPosition(String userId, Float longitude, Float latitude, String province, String city, String region);
    
    ResultDTO getMapPosition(Float neLongitude, Float neLatitude, Float swLongitude, Float swLatitude);
}
