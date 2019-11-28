package com.sinosoft.demo.infrastructure.persistance.mapper;

import com.sinosoft.demo.domain.entity.StudentExcel;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: 王黄鹏
 * @date: 2019/11/27
 * @time: 下午4:51
 * @description:
 */
public interface ExcelMapper {
    @Select("select * from student")
    List<StudentExcel> listStudent();
}
