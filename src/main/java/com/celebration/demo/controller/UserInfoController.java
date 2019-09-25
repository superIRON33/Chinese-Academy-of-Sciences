package com.celebration.demo.controller;

import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.service.UserInfoService;
import com.celebration.demo.service.WxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/celebration")
public class UserInfoController {
    
    @Autowired
    private WxService wxService;
    @Autowired
    private UserInfoService userInfoService;
    
    @PostMapping(value = "/userInfo/login")
    public ResultDTO isWxLogin(@RequestParam("code") String code,
                               @RequestParam(value = "userId", required = false) String userId,
                               @RequestParam("image") String image) {
        
        log.info("用户登录: {}(userId)，{}(name)", userId);
        return wxService.isWxLogin(code, userId, image);
    }

    @GetMapping(value = "/userInfo")
    public ResultDTO getUserInfo(@RequestParam(value = "id") String id) {

        log.info("获取用户信息: {}(id)", id);
        return userInfoService.getUserInfo(id);
    }
    
    @PostMapping(value = "/userInfo")
    public ResultDTO updateUserInfo(@RequestParam(value = "id") String id,
                                    @RequestParam(value = "name") String name,
                                    @RequestParam(value = "year") Integer year,
                                    @RequestParam(value = "institute") String institute,
                                    @RequestParam(value = "degree") String degree,
                                    @RequestParam(value = "workspace") String workspace,
                                    @RequestParam(value = "workspaceIs") Integer workspaceIs,
                                    @RequestParam(value = "address") String address,
                                    @RequestParam(value = "addressIs") Integer addressIs,
                                    @RequestParam(value = "telephone") String telephone,
                                    @RequestParam(value = "telephoneIs") Integer telephoneIs,
                                    @RequestParam(value = "emailAdd") String emailAdd,
                                    @RequestParam(value = "emailIs") Integer emailIs,
                                    @RequestParam(value = "wechatPNG") String wechatPNG,
                                    @RequestParam(value = "slogan") String slogan) {
    
        log.info("更新用户信息: {}(id)", id);
        return userInfoService.updateUserInfo(id, name, year, institute, degree, workspace, workspaceIs, address, addressIs, telephone, telephoneIs, emailAdd, emailIs, wechatPNG, slogan);
    }
}
