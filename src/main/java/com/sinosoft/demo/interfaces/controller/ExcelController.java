package com.sinosoft.demo.interfaces.controller;

import com.sinosoft.demo.domain.entity.StudentExcel;
import com.sinosoft.demo.domain.util.ExcelUtil;
import com.sinosoft.demo.infrastructure.persistance.mapper.ExcelMapper;
import com.sinosoft.demo.interfaces.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: 王黄鹏
 * @date: 2019/11/27
 * @time: 下午4:49
 * @description:
 */
@RestController
@Slf4j
@Api(value = "Excel", tags = "Excel导入导出")

public class ExcelController {
    @Resource
    private ExcelMapper excelMapper;

    @GetMapping("export")
    @ApiOperation(value = "导出", tags = "Excel导入导出")
    public Result outExcel(HttpServletResponse response) {
        List<StudentExcel> studentExcels = excelMapper.listStudent();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-hhmmss");
        String time = sdf.format(new Date());
        String fileName = "学生信息" + time;
        String sheetName = "学生信息";
        try {
            ExcelUtil.writeExcel(response, studentExcels, fileName, sheetName, new StudentExcel());
        } catch (Exception e) {
            log.info("导出异常" + e.getMessage());
            return new Result("500", "导出失败");
        }
        return new Result("200", "导出成功");
    }

    @PostMapping("import")
    @ApiOperation(value = "导入", tags = "Excel导入导出")
    public Result importExcel(MultipartFile excel) {
        Object objects = ExcelUtil.readExcel(excel, new StudentExcel(), 1, 1);
        if (objects == null) {
            return new Result("500", "导入的数据不能为空");
        }
        List<StudentExcel> studentExcels = (List<StudentExcel>) objects;
        if (studentExcels == null || studentExcels.isEmpty()) {
            return new Result("500", "导入的数据不能为空");
        }
        studentExcels.forEach(System.out::println);
        return new Result("200", "导入成功");
    }
}
