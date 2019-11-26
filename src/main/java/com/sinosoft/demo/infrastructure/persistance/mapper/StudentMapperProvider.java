package com.sinosoft.demo.infrastructure.persistance.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: 王黄鹏
 * @date: 2019/11/26
 * @time: 下午3:52
 * @description:
 */
public class StudentMapperProvider {

    public StringBuilder deleteStudent(@Param("ids") List<Integer> ids) {
        StringBuilder sb = new StringBuilder();
        sb.append("delete from student where id in(");
        for (int i = 0; i < ids.size(); i++) {
            sb.append("'" + ids.get(i) + "'");
            if (i != ids.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb;
    }
}
