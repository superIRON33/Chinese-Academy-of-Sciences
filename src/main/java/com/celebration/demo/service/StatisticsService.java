package com.celebration.demo.service;

import com.celebration.demo.model.dto.ResultDTO;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
public interface StatisticsService {
    
    ResultDTO getStatistics(String keyword);
    
    ResultDTO getMapStatistics(String keyword, Float neLongitude, Float neLatitude, Float swLongitude, Float swLatitude);
}
