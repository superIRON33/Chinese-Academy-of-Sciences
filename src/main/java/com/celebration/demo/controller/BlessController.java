package com.celebration.demo.controller;

import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.service.BlessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/celebration")
public class BlessController {

    @Autowired
    private BlessService blessService;

    @PostMapping(value = "/bless")
    public ResultDTO saveBless(@RequestParam String userId,
                               @RequestParam String content,
                               @RequestParam("image")MultipartFile image) {

        return blessService.saveBless(userId, content, image);
    }

    @GetMapping(value = "/bless")
    public ResultDTO getBless(@RequestParam Integer pageNumber,
                              @RequestParam Integer pageSize) {

        return blessService.getBless(pageNumber, pageSize);
    }

    @PostMapping(value = "/bless/commend")
    public ResultDTO commend(@RequestParam String userId,
                             @RequestParam String blessId) {
        return blessService.commend(userId, blessId);
    }
}
