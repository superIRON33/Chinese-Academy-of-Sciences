package com.celebration.demo.controller;

import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.service.BlessService;
import com.celebration.demo.service.base.QiniuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/celebration")
public class BlessController {

    @Autowired
    private BlessService blessService;
    @Autowired
    private QiniuService qiniuService;
    
    @PostMapping(value = "/bless")
    public ResultDTO saveBless(@RequestParam String userId,
                               @RequestParam String content,
                               @RequestParam(value = "image", required = false) String image) {

        return blessService.saveBless(userId, content, image);
    }

    @GetMapping(value = "/bless")
    public ResultDTO getBless(@RequestParam String userId,
                              @RequestParam Integer pageNumber,
                              @RequestParam Integer pageSize) {

        return blessService.getBless(userId, pageNumber, pageSize);
    }

    @PostMapping(value = "/bless/commend")
    public ResultDTO commend(@RequestParam String userId,
                             @RequestParam String blessId) {
        return blessService.commend(userId, blessId);
    }
    
    @GetMapping(value = "/uptoken")
    public String getUptoken() {
        
        return qiniuService.getToken();
    }
}
