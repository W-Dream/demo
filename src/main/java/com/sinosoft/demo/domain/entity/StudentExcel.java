package com.sinosoft.demo.domain.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

/**
 * @author: 王黄鹏
 * @date: 2019/11/27
 * @time: 下午4:34
 * @description:
 */
@Data
public class StudentExcel extends BaseRowModel {

    @ExcelProperty(value = {"ID"}, index = 0)
    private Integer id;

    @ExcelProperty(value = {"姓名"}, index = 1)
    private String name;

    @ExcelProperty(value = {"生日"}, index = 2)
    private Date birthday;
}
