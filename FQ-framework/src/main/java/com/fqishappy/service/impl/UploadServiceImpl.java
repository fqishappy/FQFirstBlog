package com.fqishappy.service.impl;

import com.fqishappy.constants.SystemConstants;
import com.fqishappy.domain.ResponseResult;
import com.fqishappy.service.UploadService;
import com.fqishappy.utils.AliOssUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.time.LocalDate;

/**
 * @author fqishappy
 * @date 2024/9/17 13:40
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Autowired
    private AliOssUtil aliOssUtil;

    @Override
    public ResponseResult uploadImg(MultipartFile img) {
        System.out.println("开始");
        if (img.isEmpty()) {
            System.out.println("空");
        } else {
            System.out.println(img.getOriginalFilename());
        }
        //获得原始文件名
        String originalFilename = img.getOriginalFilename();
        //判断文件类型
        String substring = null;
        if (originalFilename != null) {
            //获得文件后缀名
            substring = originalFilename.substring(originalFilename.lastIndexOf("."));
            if (!(substring.equals(".jpg") || substring.equals(".png"))) {
                return ResponseResult.errorResult(SystemConstants.FILETYPE_ERROR,"文件类型" + substring + "不符");
            }
        } else {
            return ResponseResult.errorResult(SystemConstants.FILE_NAME_NULL, "文件名为空");
        }
        String url = uploadOSS(img, substring);
        if (StringUtil.isNullOrEmpty(url)) {
            return ResponseResult.errorResult(SystemConstants.UPLOAD_FAILED, "上传失败");
        } else {
            return ResponseResult.okResult(url);
        }
    }

    private String uploadOSS(MultipartFile img, String substring) {
        log.info("文件上传：{}", img);
        try {

            LocalDate date = LocalDate.now();
            String time = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
            String objectName = time + UUID.randomUUID().toString() + substring;
            log.info("文件名：{}", objectName);
            String filePath = aliOssUtil.upload(img.getBytes(), objectName);
            return filePath;
        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage());
        }
        return null;
    }
}
