package com.celebration.demo.service;

import com.celebration.demo.model.dto.ResultDTO;
import org.springframework.web.multipart.MultipartFile;

public interface BlessService {

    ResultDTO saveBless(String userId, String content, MultipartFile image);

    ResultDTO getBless(Integer pageNumber, Integer pageSize);
}
