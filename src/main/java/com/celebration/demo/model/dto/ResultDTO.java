package com.celebration.demo.model.dto;

import com.celebration.demo.common.enums.ResultEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: wjy
 * @date: 2019/7/30
 * @description: 封装返回给前端的数据
 */
@Data
@NoArgsConstructor
public class ResultDTO {

    private Integer code;
    private String message;
    private Object data;

    public ResultDTO(ResultEnum result) {
        setResultEnum(result);
    }

    public void setResultEnum(ResultEnum result) {
        
        this.code = result.getCode();
        this.message = result.getMessage();
    }
}