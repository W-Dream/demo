package com.sinosoft.demo.interfaces.controller;

import com.github.pagehelper.PageInfo;
import com.sinosoft.demo.application.service.StudentService;
import com.sinosoft.demo.domain.entity.Student;
import com.sinosoft.demo.interfaces.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author: 王黄鹏
 * @date: 2019/11/26
 * @time: 下午2:39
 * @description:
 */
@RestController
@Api(value = "学生信息管理", tags = "学生信息管理")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "新增", tags = "学生信息管理")
    @PostMapping("save")
    public Result saveStudent(@RequestBody List<Student> students) {
        boolean saveResult = studentService.saveStudent(students);
        if (saveResult) {
            return new Result("200", "保存成功");
        }
        return new Result("500", "保存失败");
    }

    @ApiOperation(value = "修改", tags = "学生信息管理")
    @PostMapping("update")
    public Result updateStudent(@RequestBody Map<Integer, Student> students) {
        boolean updateResult = studentService.updateStudent(students);
        if (updateResult) {
            return new Result("200", "修改成功");
        }
        return new Result("500", "修改失败");
    }

    @ApiOperation(value = "删除", tags = "学生信息管理")
    @DeleteMapping("delete")
    public Result deleteStudent(@RequestBody List<Integer> ids) {
        boolean deleteResult = studentService.deleteStudent(ids);
        if (deleteResult) {
            return new Result("200", "删除成功");
        }
        return new Result("500", "删除失败");
    }

    @ApiOperation(value = "查询", tags = "学生信息管理")
    @GetMapping("list")
    public Result listStudent(int pages, int rows, @RequestParam("ids") List<Integer> ids) {
        PageInfo<Student> students = studentService.listStudent(pages, rows, ids);
        if (students != null && !students.getList().isEmpty()) {
            return new Result("200", "查询成功", students);
        }
        return new Result("500", "查询失败");
    }
}
