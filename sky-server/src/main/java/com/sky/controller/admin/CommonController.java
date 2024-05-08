package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api(tags = "图片上传")
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    @ApiOperation("菜品图片上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        log.info("图片开始上传");
        String filePathName = "";
        filePathName = UUID.randomUUID().toString() + System.currentTimeMillis() + file.getOriginalFilename();
        String url;
        try {

            url = aliOssUtil.upload(file.getBytes(), filePathName);
            return Result.success(url);
        } catch (IOException e) {
            log.error("文件上传失败{}",e);
            return Result.error(MessageConstant.UPLOAD_FAILED);
        }

    }

}
