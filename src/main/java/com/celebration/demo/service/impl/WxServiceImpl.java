package com.celebration.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.celebration.demo.common.enums.ResultEnum;
import com.celebration.demo.common.enums.VariableEnum;
import com.celebration.demo.common.enums.WechatEnum;
import com.celebration.demo.common.utils.HttpClientUtil;
import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.model.entity.UserInfo;
import com.celebration.demo.repository.UserInfoRepository;
import com.celebration.demo.service.WxService;
import com.celebration.demo.service.base.RedisOperator;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Auther: wjy
 * @Date: 2019/9/25 12:31
 * @Description: 微信登录服务层实现类
 */
@Slf4j
@Service
public class WxServiceImpl implements WxService {

	@Autowired
	private RedisOperator redisOperator;
    @Autowired
    private UserInfoRepository userInfoRepository;
	
	@Override
	public String getAccessToken() {
	 
		// access_token为null或超时
		if (!redisOperator.hasKey("access_token")) {
			// GET请求传递的参数的集合
			Map<String, String> param = new HashMap<>();
			param.put("grant_type", "client_credential");
			param.put("appid", WechatEnum.APP_ID.getValue());
			param.put("secret", WechatEnum.SECRET.getValue());
			// 发起GET请求
			String wxResult = HttpClientUtil.doGet(WechatEnum.WX_ACCESS_TOKEN.getValue(), param);
			// 解析Json数据
			JSONObject jsonObject = JSONObject.parseObject(wxResult);

			// 请求成功
			if (jsonObject.getString("access_token") != null) {
				// 存入redis数据库
				redisOperator.set("access_token", jsonObject.getString("access_token"), Long.parseLong(jsonObject.getString("expires_in")));
			}
			// 请求失败
			else {
				log.error("access_token请求失败");
			}
		}

		return redisOperator.getValue("access_token");
	}
	
	@Override
	public ResultDTO isWxLogin(String code, String userId, String image) {
        
        Optional<UserInfo> userInfo = null;
	    
	    if (!StringUtil.isNullOrEmpty(userId)) {
            userInfo = userInfoRepository.findById(userId);
        }
	    // 用户第一次登录
	    if (userInfo == null || !userInfo.isPresent()) {
            userId = userInfoRepository.save(new UserInfo(image)).getId();
        }
        // 更新旧用户的image
        else {
            // 更新头像
            if (!userInfo.get().getImage().equals(image)) {
                userInfoRepository.updateImage(userId, image);
            }
            // 登录态尚未失效
            if (redisOperator.hasKey(userId)) {
                return new ResultDTO(ResultEnum.SUCCESS);
            }
        }
        // 重新维护登录态
        return wxLogin(userId, code);
	}
    
    @Override
    public ResultDTO wxLogin(String userId, String code) {
	    
        // GET请求传递的参数的集合
        Map<String, String> param = new HashMap<>();
        param.put("appid", WechatEnum.APP_ID.getValue());
        param.put("secret", WechatEnum.SECRET.getValue());
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");
        // 发起GET请求
        String wxResult = HttpClientUtil.doGet(WechatEnum.WX_LOGIN.getValue(), param);
        // 解析Json数据
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        log.info(jsonObject.getString("openid"));
        
        if (jsonObject.getString("openid") != null) {
            // 存入redis数据库
            redisOperator.set(userId, jsonObject.getString("openid"), VariableEnum.LOGIN_TIMEOUT.getValue());
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(userId);
            return resultDTO;
        }
        userInfoRepository.deleteById(userId);
        log.error("errcode:{}，errmsg: {}", jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
        return new ResultDTO(ResultEnum.WECHAT_FAIL);
    }
}
