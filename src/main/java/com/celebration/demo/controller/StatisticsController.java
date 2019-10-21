package com.celebration.demo.controller;

import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/celebration")
public class StatisticsController {
    
    @Autowired
    private StatisticsService statisticsService;
    
    @GetMapping(value = "/statistics")
    public ResultDTO getStatistics(@RequestParam("keyword") String keyword) {
        
        return statisticsService.getStatistics(keyword);
    }
    
    @GetMapping(value = "/statistics/map")
    public ResultDTO getMapStatistics(@RequestParam("keyword") String keyword,
                                      @RequestParam("neLongitude") Float neLongitude,
                                      @RequestParam("neLatitude") Float neLatitude,
                                      @RequestParam("swLongitude") Float swLongitude,
                                      @RequestParam("swLatitude") Float swLatitude) {
        return statisticsService.getMapStatistics(keyword, neLongitude, neLatitude, swLongitude, swLatitude);
    }
}
