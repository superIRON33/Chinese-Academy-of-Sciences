package com.celebration.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlessDTO {

    private String id;
    private String name;
    private String userImage;
    private String institute;
    private String image;
    private String content;

}
