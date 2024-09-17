package com.fqishappy.service;

import com.fqishappy.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fqishappy
 * @date 2024/9/17 13:40
 */
public interface UploadService {

    /**
     * 上传图片
     * @param img
     * @return
     */
    ResponseResult uploadImg(MultipartFile img);
}
