package com.celebration.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wjy
 * @date: 2019/
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapUserInfoDTO {
    
    private String id;
    private String name;
    private String image;
    private Integer year;
    private String institute;
    private String workspace;
    private String emailAdd;
}
