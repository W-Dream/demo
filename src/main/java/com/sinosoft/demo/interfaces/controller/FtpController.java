package com.sinosoft.demo.interfaces.controller;

import com.sinosoft.demo.domain.entity.FtpEntity;
import com.sinosoft.demo.domain.util.FtpUtil;
import com.sinosoft.demo.interfaces.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: 王黄鹏
 * @date: 2019/11/28
 * @time: 下午6:04
 * @description:
 **/


@RestController
@Slf4j
@Api(value = "Ftp", tags = "Ftp上传下载")
public class FtpController {
    @Autowired
    private FtpUtil ftpUtil;

    @PostMapping("ftp/upload")
    @ApiOperation(value = "上传", tags = "Ftp上传下载")
    public Result uploadFtp(@RequestBody MultipartFile file, String filename) {
        try {
            ftpUtil.uploadFile(file.getInputStream(), filename);
            return new Result("200", "上传成功");
        } catch (Exception e) {
            log.info("上传异常");
            return new Result("500", "上传失败");
        }
    }

    @GetMapping("ftp/download")
    @ApiOperation(value = "下载", tags = "Ftp上传下载")
    public Result downloadFtp(String remoteFileName, String localFileName) {
        try {
            ftpUtil.downloadFile(remoteFileName, localFileName);
            return new Result("200", "下载成功");
        } catch (Exception e) {
            log.info("下载异常");
            return new Result("500", "下载失败");
        }
    }
}
