package com.sinosoft.demo.domain.repository;

import com.github.pagehelper.PageInfo;
import com.sinosoft.demo.domain.entity.Student;

import java.util.List;
import java.util.Map;

/**
 * @author: 王黄鹏
 * @date: 2019/11/26
 * @time: 下午2:44
 * @description:
 */
public interface StudentRepository {
    /**
     * 保存
     *
     * @param students
     * @return boolean
     */
    boolean saveStudent(List<Student> students);

    /**
     * 修改
     *
     * @param students
     * @return boolean
     */
    boolean updateStudent(Map<Integer, Student> students);

    /**
     * 删除
     *
     * @param ids
     * @return boolean
     */
    boolean deleteStudent(List<Integer> ids);

    /**
     * 查询
     *
     * @param pages 页码
     * @param rows  列数
     * @param ids   id
     * @return PageInfo<Student>
     */
    PageInfo<Student> listStudent(int pages, int rows, List<Integer> ids);
}
