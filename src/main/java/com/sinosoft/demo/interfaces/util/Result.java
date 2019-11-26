package com.sinosoft.demo.interfaces.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "返回响应数据")
@Data
@AllArgsConstructor
public class Result {

    @ApiModelProperty(value = "状态码")
    private String code;
    @ApiModelProperty(value = "错误信息")
    private String message;
    @ApiModelProperty(value = "返回的数据")
    private Object data;

    public Result(String code,String message){
        this.code = code;
        this.message = message;
    }

}