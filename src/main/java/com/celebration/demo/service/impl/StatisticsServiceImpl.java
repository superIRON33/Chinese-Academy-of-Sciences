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
        return resultDTO;
    }
}
