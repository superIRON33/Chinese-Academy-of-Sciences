package com.celebration.demo.controller;

import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/celebration")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping(value = "/userInfo")
    public ResultDTO getUserInfo(@RequestParam String id) {

        return userInfoService.getUserInfo(id);
    }

}
