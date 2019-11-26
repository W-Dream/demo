package com.sinosoft.demo.application.service;

import com.github.pagehelper.PageInfo;
import com.sinosoft.demo.domain.entity.Student;
import com.sinosoft.demo.domain.service.StudentOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 王黄鹏
 * @date: 2019/11/26
 * @time: 下午2:43
 * @description:
 */
@Service
public class StudentService {

    @Autowired
    private StudentOperationService studentOperationService;

    public boolean saveStudent(List<Student> students) {
        return studentOperationService.saveStudent(students);
    }

    public boolean updateStudent(Map<Integer, Student> students) {
        return studentOperationService.updateStudent(students);
    }

    public boolean deleteStudent(List<Integer> ids) {
        return studentOperationService.deleteStudent(ids);
    }

    public PageInfo<Student> listStudent(int pages, int rows, List<Integer> ids) {
        return studentOperationService.listStudent(pages, rows, ids);
    }
}
