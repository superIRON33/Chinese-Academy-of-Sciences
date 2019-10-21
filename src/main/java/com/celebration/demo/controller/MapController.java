package com.celebration.demo.controller;

import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
@RestController
@RequestMapping("/celebration")
public class MapController {
    
    @Autowired
    private MapService mapService;
    
    @GetMapping("/map/userInfo")
    public ResultDTO getMapUserInfo(@RequestParam("userId") String userId) {

        return mapService.getMapUserInfo(userId);
    }
    
    @PostMapping("/map")
    public ResultDTO saveMapPosition(@RequestParam("userId") String userId,
                                     @RequestParam("longitude") Float longitude,
                                     @RequestParam("latitude") Float latitude,
                                     @RequestParam("province") String province,
                                     @RequestParam("city") String city,
                                     @RequestParam("region") String region
    ) {
        
        return mapService.saveMapPosition(userId, longitude, latitude, province, city, region);
    }
    
    @GetMapping("/map")
    public ResultDTO getMapPostion(@RequestParam("neLongitude") Float neLongitude,
                                   @RequestParam("neLatitude") Float neLatitude,
                                   @RequestParam("swLongitude") Float swLongitude,
                                   @RequestParam("swLatitude") Float swLatitude) {
        
        return mapService.getMapPosition(neLongitude, neLatitude, swLongitude, swLatitude);
    }
}
