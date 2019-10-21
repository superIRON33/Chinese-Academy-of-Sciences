package com.celebration.demo.service;

import com.celebration.demo.model.dto.ResultDTO;

public interface BlessService {

    ResultDTO saveBless(String userId, String content, String image);

    ResultDTO getBless(String userId, Integer pageNumber, Integer pageSize);

    ResultDTO commend(String userId, String blessId);
}
