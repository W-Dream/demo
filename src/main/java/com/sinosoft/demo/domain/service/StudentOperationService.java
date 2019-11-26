package com.sinosoft.demo.domain.service;

import com.github.pagehelper.PageInfo;
import com.sinosoft.demo.domain.entity.Student;
import com.sinosoft.demo.domain.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: 王黄鹏
 * @date: 2019/11/26
 * @time: 下午2:44
 * @description:
 */
@Service
@Slf4j
public class StudentOperationService {

    @Autowired
    private StudentRepository studentRepository;

    public boolean saveStudent(List<Student> students) {
        try {
            return studentRepository.saveStudent(students);
        } catch (Exception e) {
            log.error("调用StudentOperationService.saveStudent(List<Student> students)报错" + e.getMessage());
        }
        return false;
    }

    public boolean updateStudent(Map<Integer, Student> students) {
        try {
            return studentRepository.updateStudent(students);
        } catch (Exception e) {
            log.error("调用StudentOperationService.updateStudent(Map<Integer, Student> students)报错" + e.getMessage());
        }
        return false;
    }

    public boolean deleteStudent(List<Integer> ids) {
        try {
            return studentRepository.deleteStudent(ids);
        } catch (Exception e) {
            log.error("调用StudentOperationService.deleteStudent(List<Integer> ids)报错" + e.getMessage());
        }
        return false;
    }

    public PageInfo<Student> listStudent(int pages, int rows, List<Integer> ids) {
        try {
            return studentRepository.listStudent(pages, rows, ids);
        } catch (Exception e) {
            log.error("调用StudentOperationService.listStudent(int pages, int rows, List<Integer> ids)报错" + e.getMessage());
        }
        return null;
    }
}
