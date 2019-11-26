package com.sinosoft.demo.infrastructure.repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sinosoft.demo.domain.entity.Student;
import com.sinosoft.demo.domain.repository.StudentRepository;
import com.sinosoft.demo.infrastructure.persistance.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: 王黄鹏
 * @date: 2019/11/26
 * @time: 下午2:45
 * @description:
 */
@Service
@Slf4j
@Transactional(rollbackForClassName = "RuntimeSqlException.class")
public class StudentRepositoryImpl implements StudentRepository {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public boolean saveStudent(List<Student> students) {
        int saveResutl = studentMapper.saveStudent(students);
        if (saveResutl > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudent(Map<Integer, Student> students) {
        int updateResutl = studentMapper.updateStudent(students);
        if (updateResutl > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudent(List<Integer> ids) {
        int deleteResutl = studentMapper.deleteStudent(ids);
        if (deleteResutl > 0) {
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<Student> listStudent(int pages, int rows, List<Integer> ids) {
        PageHelper.startPage(pages, rows);
        List<Student> students = studentMapper.listStudent(ids);
        return new PageInfo<>(students);
    }
}
