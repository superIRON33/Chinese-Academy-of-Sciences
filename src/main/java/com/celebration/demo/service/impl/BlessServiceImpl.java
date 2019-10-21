package com.celebration.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.celebration.demo.common.enums.ResultEnum;
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
import com.celebration.demo.service.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
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

    @Override
    public ResultDTO saveBless(String userId, String content, String image) {

        if (userInfoRepository.findById(userId).isPresent()) {
            if (image == null) {
                image = "";
            }
            String cert = RandomNumberUtil.getRandomNumber();
            blessRepository.save(new Bless(userId, content, image, cert));
            Map<String, Object> map = new HashMap<>();
            map.put("count", blessRepository.countAllBy());
            map.put("cert", cert);
            ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
            resultDTO.setData(map);
            return resultDTO;
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
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
                    bless.getImage(), bless.getContent(), bless.getLikes(), bless.getCert(), value);
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
