package com.celebration.demo.service.base;

import com.alibaba.fastjson.JSON;
import com.qiniu.util.Auth;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wjy
 * @date: 2019/9/28
 * @description: 七牛云上传凭证
 */
@Slf4j
@Service
public class QiniuService {

    /**
     * 七牛access_key
     */
    private static final String ACCESS_KEY = "R3rt7L1UklncNpPwUcg2henI3PuK9dphLH51yVvu";

    /**
     * 七牛secret_key
     */
    private static final String SECRET_KEY = "b1RMynHJubsbdwGrF9QVOvgvePJrlUmkAaB4HPkQ";

    /**
     * 七牛bucket
     */
    private static final String BUCKET = "xdxlb";

    public String getToken() {
        
        Map<String, String> returnParam = new HashMap<>();
        // 验证七牛云身份是否通过
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        // 生成凭证
        String token = auth.uploadToken(BUCKET);
        if (StringUtil.isNullOrEmpty(token)) {
            log.error("生成上传凭证失败，token: {}", token);
        }
        returnParam.put("uptoken", token);
        return JSON.toJSONString(returnParam);
    }
}