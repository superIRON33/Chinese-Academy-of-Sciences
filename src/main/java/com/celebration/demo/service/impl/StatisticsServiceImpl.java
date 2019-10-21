package com.celebration.demo.service.impl;

import com.celebration.demo.common.enums.ResultEnum;
import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.repository.StatisticsRepository;
import com.celebration.demo.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    
    @Autowired
    private StatisticsRepository statisticsRepository;
    
    @Override
    public ResultDTO getStatistics(String keyword) {
        
        ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
        if (keyword.equals("研究所")) {
            resultDTO.setData(statisticsRepository.statisticsInstitute());
        }
        else if (keyword.equals("省份")) {
            resultDTO.setData(statisticsRepository.statisticsProvince());
        }
        else if (keyword.equals("国家")) {
            resultDTO.setData(statisticsRepository.statisticsCountry());
        }
        return resultDTO;
    }
    
    @Override
    public ResultDTO getMapStatistics(String keyword, Float neLongitude, Float neLatitude, Float swLongitude, Float swLatitude) {

        Object data = "";
        if (keyword.equals("省")) {
            System.out.println("省");
            data = statisticsRepository.statisticsMapProvince(swLongitude, neLongitude, swLatitude, neLatitude);
        }
        else if (keyword.equals("市")) {
            System.out.println("市");
            data = statisticsRepository.statisticsMapCity(swLongitude, neLongitude, swLatitude, neLatitude);
        }
        else if (keyword.equals("区")) {
            data = statisticsRepository.statisticsMapRegion(swLongitude, neLongitude, swLatitude, neLatitude);
        }
        ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
        resultDTO.setData(data);
        return resultDTO;
    }
}
