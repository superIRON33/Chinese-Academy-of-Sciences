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
public class UserLocationDTO {
    
    private String id;
    private String image;
    private Float longitude;
    private Float latitude;
}
