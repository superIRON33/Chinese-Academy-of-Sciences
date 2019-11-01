package com.celebration.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.celebration.demo.common.enums.ResultEnum;
import com.celebration.demo.common.enums.WechatEnum;
import com.celebration.demo.common.utils.HttpClientUtil;
import com.celebration.demo.common.utils.ImageUploadUtil;
import com.celebration.demo.common.utils.RandomNumberUtil;
import com.celebration.demo.model.dto.BlessDTO;
import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.model.entity.Bless;
import com.celebration.demo.model.entity.Commend;
import com.celebration.demo.model.entity.UserInfo;
import com.celebration.demo.repository.BlessRepository;
import com.celebration.demo.repository.CommendRepository;
import com.celebration.demo.repository.UserInfoRepository;
import com.celebration.demo.service.BlessService;
import com.celebration.demo.service.WxService;
import com.celebration.demo.service.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

@Service
public class BlessServiceImpl implements BlessService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private BlessRepository blessRepository;

    @Autowired
    private GenericService genericService;

    @Autowired
    private CommendRepository commendRepository;

    @Autowired
    private WxService wxService;
    
    @Value("${web.upload-path}")
    private String path;

    @Override
    public ResultDTO saveBless(String userId, String content, MultipartFile image) {
    
        String url = WechatEnum.CONTENT_DETECTION.getValue() + wxService.getAccessToken();
        System.out.println(url);
        Map<String, String> param = new HashMap();
        param.put("content", content);
        // 发起POST请求
        System.out.println(JSON.toJSONString(param));
        String wxResult = HttpClientUtil.doPost1(url, JSON.toJSONString(param));
        // 解析Json数据
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        int code = (int) jsonObject.get("errcode");
        System.out.println(code);
        if (code == 0) {
            if (userInfoRepository.findUserInfoById(userId).isPresent()) {
                Bless bless = new Bless(userId, content);
                if (image != null) {
                    String[] strings = ImageUploadUtil.upload(image, path, image.getOriginalFilename());
                    if (strings != null) {
                        String imageURL = "https://action.ucas.ac.cn/images/" + strings[1];
                        System.out.println(imageURL);
                        bless.setImage(imageURL);
                        blessRepository.saveAndFlush(bless);
                        if (bless.getImage().equals("")) {
                            return new ResultDTO(ResultEnum.IMAGE_UPLOAD_FAILURE);
                        }
                        Map<String, Object> map = new HashMap<>();
                        map.put("imageURL", imageURL);
                        map.put("count", blessRepository.countUserInfo());
                        map.put("cert", RandomNumberUtil.getRandomNumber());
                        ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
                        resultDTO.setData(map);
                        return resultDTO;
    
                    }
                    ResultDTO resultDTO = new ResultDTO(ResultEnum.IMAGE_UPLOAD_FAILURE);
                    resultDTO.setData(null);
                    return resultDTO;
                }
                Map<String, Object> map = new HashMap<>();
                blessRepository.saveAndFlush(bless);
                map.put("imageURL", "");
                map.put("count", blessRepository.countUserInfo());
                map.put("cert", RandomNumberUtil.getRandomNumber());
                ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
                resultDTO.setData(map);
                return resultDTO;
            }
            return new ResultDTO(ResultEnum.ID_INVALID);
        }
        return new ResultDTO(ResultEnum.CONTENT_ERROR);
    }

    @Override
    public ResultDTO getBless(String userId, Integer pageNumber, Integer pageSize) {

        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Page<Bless> page = blessRepository.findAllBy(new PageRequest(pageNumber, pageSize, sort));
        List<Bless> blesses = page.getContent();
        List<BlessDTO> blessDTOList = new ArrayList<>();
        blesses.forEach(bless -> {
            Optional<UserInfo> userInfo = userInfoRepository.findUserInfoById(bless.getUserId());
            Optional<Commend> commend = commendRepository.findCommendByUserIdAndBlessId(userId, bless.getId());
            int value = 0;
            if (commend.isPresent()) {
                value = 1;
            }
            BlessDTO blessDTO = new BlessDTO(bless.getId(),
                    userInfo.get().getName(),
                    userInfo.get().getImage(),
                    userInfo.get().getInstitute(),
                    bless.getImage(), bless.getContent(), bless.getLikes(),  value);
            blessDTOList.add(blessDTO);
        });
        if (blesses.size() != 0) {
            Map map = genericService.pagination(page);
            map.put("blessList", blessDTOList);
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(JSON.toJSON(map));
            return resultDTO;
        } else {
            return new ResultDTO(ResultEnum.DATA_NOT_EXIST);
        }
    }

    @Override
    public ResultDTO commend(String userId, String blessId) {

        Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
        Optional<Bless> bless = blessRepository.findById(blessId);
        if (userInfo.isPresent() && bless.isPresent()) {
            Optional<Commend> commend = commendRepository.findCommendByUserIdAndBlessId(userId, blessId);
            if (commend.isPresent()) {
                return new ResultDTO(ResultEnum.COMMEND_EXIST);
            } else {
                Commend commend1 = new Commend(userId, blessId);
                commendRepository.saveAndFlush(commend1);
                bless.get().setLikes(bless.get().getLikes() + 1);
                blessRepository.saveAndFlush(bless.get());
                return new ResultDTO(ResultEnum.SUCCESS);
            }
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }
}
