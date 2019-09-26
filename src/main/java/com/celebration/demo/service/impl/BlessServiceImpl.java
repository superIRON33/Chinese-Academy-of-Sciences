package com.celebration.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.celebration.demo.common.enums.ResultEnum;
import com.celebration.demo.common.utils.ImageNameUtil;
import com.celebration.demo.common.utils.ImageUploadUtil;
import com.celebration.demo.model.dto.BlessDTO;
import com.celebration.demo.model.dto.ResultDTO;
import com.celebration.demo.model.entity.Bless;
import com.celebration.demo.repository.BlessRepository;
import com.celebration.demo.repository.UserInfoRepository;
import com.celebration.demo.service.BlessService;
import com.celebration.demo.service.base.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlessServiceImpl implements BlessService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private BlessRepository blessRepository;

    @Autowired
    private GenericService genericService;

    @Value("${web.upload-path}")
    private String path;

    @Override
    public ResultDTO saveBless(String userId, String content, MultipartFile image) {

        if (userInfoRepository.findUserInfoById(userId).isPresent()) {
            Bless bless = new Bless(userId, content);
            if (ImageUploadUtil.upload(image, path, image.getOriginalFilename())){
                bless.setImage(path + ImageNameUtil.getImageName(image.getOriginalFilename()));
                blessRepository.saveAndFlush(bless);
//                System.out.println(bless.getImage());
                Map<String, Object> map = new HashMap<>();
                map.put("imageURL", path + bless.getImage());
                map.put("count", blessRepository.countAllBy());
                ResultDTO resultDTO = new ResultDTO(ResultEnum.SUCCESS);
                resultDTO.setData(map);
                return resultDTO;
            }
            return new ResultDTO(ResultEnum.IMAGE_UPLOAD_FAILURE);
        }
        return new ResultDTO(ResultEnum.ID_INVALID);
    }

    @Override
    public ResultDTO getBless(Integer pageNumber, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Page<Bless> page = blessRepository.findAllBy(new PageRequest(pageNumber, pageSize, sort));

        List<Bless> blesses = page.getContent();
        List<BlessDTO> blessDTOList = new ArrayList<>();
        blesses.forEach(bless -> {
            BlessDTO blessDTO = new BlessDTO(bless.getId(),
                    userInfoRepository.findUserInfoById(bless.getUserId()).get().getName(),
                    userInfoRepository.findUserInfoById(bless.getUserId()).get().getImage(),
                    userInfoRepository.findUserInfoById(bless.getUserId()).get().getInstitute(),
                    bless.getImage(), bless.getContent());
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
}
