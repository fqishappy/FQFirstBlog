package com.fqishappy.controller;

import com.fqishappy.domain.ResponseResult;
import com.fqishappy.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author fqishappy
 * @date 2024/9/21 16:46
 */
@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 上传图片至oss
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult uploadImg(@RequestParam("img") MultipartFile multipartFile) {
        return uploadService.uploadImg(multipartFile);
    }
}
