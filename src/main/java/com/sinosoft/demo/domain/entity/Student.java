package com.sinosoft.demo.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author: 王黄鹏
 * @date: 2019/11/26
 * @time: 下午2:46
 * @description:
 */
@Data
@ApiModel(value = "学生信息", description = "学生信息")
public class Student {

    @ApiModelProperty(name = "id", value = "ID", required = true, dataType = "Integer")
    private Integer id;

    @ApiModelProperty(name = "name", value = "姓名", required = true, dataType = "String")
    private String name;

    @ApiModelProperty(name = "birthday", value = "生日", required = true, dataType = "Date")
    private Date birthday;
}
